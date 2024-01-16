package com.bifos.fospay.remittance.application.service

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.remittance.adapter.out.persistence.RemittanceRequestMapper
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceCommand
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceUseCase
import com.bifos.fospay.remittance.application.port.out.RequestRemittancePort
import com.bifos.fospay.remittance.application.port.out.banking.BankingPort
import com.bifos.fospay.remittance.application.port.out.membership.MembershipPort
import com.bifos.fospay.remittance.application.port.out.money.MoneyPort
import com.bifos.fospay.remittance.domain.RemittanceRequest
import org.springframework.transaction.annotation.Transactional
import kotlin.math.ceil

@UseCase
@Transactional
class RequestRemittanceService(
    private val requestRemittancePort: RequestRemittancePort,
    private val remittanceRequestMapper: RemittanceRequestMapper,
    private val membershipPort: MembershipPort,
    private val moneyPort: MoneyPort,
    private val bankingPort: BankingPort
) : RequestRemittanceUseCase {

    override fun requestRemittance(command: RequestRemittanceCommand): RemittanceRequest? {
        command.fromMembershipId!!
        command.toBankAccountNumber!!
        command.toBankName!!
        command.remittanceType!!
        command.amount!!

        // 0. 송금 요청 상태로 기록 (persistence layer)
        val entity = requestRemittancePort.createRemittanceRequestHistory(command)

        // 1. from 멤버십 상태 확인 (membership Svc)
        val membershipStatus = membershipPort.getMembershipStatus(command.fromMembershipId)
        if (!membershipStatus.isValid) {
            return null
        }

        // 2. 잔액 존재하는지 확인 (money Svc)
        val moneyInfo = moneyPort.getMoneyInfo(command.fromMembershipId)
        if (moneyInfo.balance < command.amount) {
            // 2-1. 잔액이 충분하지 않다면 충전 요청 (money Svc)
            val rechargeAmount = (ceil((command.amount - moneyInfo.balance) / 10000.0) * 10000).toInt()
            val moneyResult = moneyPort.requestMoneyRecharging(command.fromMembershipId, rechargeAmount)
            if (!moneyResult) {
                return null
            }
        }


        // 3. 송금 타입 (고객/은행)
        if (command.remittanceType == 0) {
            val remittanceResult1 = moneyPort.requestMoneyDecrease(command.fromMembershipId, command.amount)
            val remittanceResult2 = moneyPort.requestMoneyIncrease(command.toMembershipId!!, command.amount)

            if (remittanceResult1 || remittanceResult2) {
                return null
            }
        } else if (command.remittanceType == 1) {
            val result = bankingPort.requestFirmBanking(command.toBankName, command.toBankAccountNumber, command.amount)
            if (!result) {
                return null
            }
        } else {
            return null
        }

        // 3-1. 내부 고객일 경우
        // from 고객 머니 감액, to 고객 머니 증액

        // 3-2. 외부 은행 계좌
        // 외부 은행 계좌가 적절한지 확인 (banking Svc)
        // 법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking Svc)

        // 4. 송금 요청 상태를 성공으로 기록 (persistence layer)
        // 4. 송금 기록 (persistence layer) // 송금이 완료된 기록

        entity.remittanceStatus = "success"
        val result = requestRemittancePort.saveRemittanceRequestHistory(entity)
        if (result) {
            return remittanceRequestMapper.mapToDomainEntity(entity)
        }

        return null
    }
}