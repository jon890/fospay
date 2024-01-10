package com.bifos.fospay.money.application.service

import com.bifos.fospay.money.adapter.out.persistence.MoneyChangingRequestMapper
import com.bifos.fospay.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.bifos.fospay.money.application.port.`in`.IncreaseMoneyRequestUseCase
import com.bifos.fospay.money.application.port.out.IncreaseMoneyPort
import com.bifos.fospay.money.domain.MoneyChangingRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class IncreaseMoneyRequestService(
    private val increaseMoneyPort: IncreaseMoneyPort,
    private val moneyChangingRequestMapper: MoneyChangingRequestMapper
) : IncreaseMoneyRequestUseCase {

    override fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {

        // 머니의 충전, 증액이라는 과정
        // 1. 고객 정보가 정상인지 확인 (멤버)

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)

        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)

        // 4. 증액을 위한 기록, 요청 상태로 MoneyChangingRequest를 생성한다.

        // 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 => fospay 법인 계좌) (뱅킹)

        // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴

        // 성공 시에 멤버의 MeberMoney 값 증액이 필요해요
        val memberMoney = increaseMoneyPort.increaseMoney(command.targetMembershipId!!, balance = command.amount!!)

        // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        val entity = increaseMoneyPort.createMoneyChangingRequest(
            targetMembershipId = command.targetMembershipId,
            moneyChangingType = 0,
            changingMoneyAmount = command.amount,
            moneyChangingStatus = 1,
            uuid = UUID.randomUUID()
        )

        return moneyChangingRequestMapper.mapToDomainEntity(entity)
    }
}
