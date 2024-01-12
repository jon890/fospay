package com.bifos.fospay.banking.application.service

import com.bifos.fospay.banking.adapter.out.external.bank.GetBankAccountRequest
import com.bifos.fospay.banking.adapter.out.persistence.RegisteredBankAccountMapper
import com.bifos.fospay.banking.application.port.`in`.RegisterBankAccountCommand
import com.bifos.fospay.banking.application.port.`in`.RegisterBankAccountUseCase
import com.bifos.fospay.banking.application.port.out.GetMembershipPort
import com.bifos.fospay.banking.application.port.out.RegisterBankAccountPort
import com.bifos.fospay.banking.application.port.out.RequestBankAccountInfoPort
import com.bifos.fospay.banking.domain.RegisteredBankAccount
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterBankAccountService(
    private val registerBankAccountPort: RegisterBankAccountPort,
    private val registeredBankAccountMapper: RegisteredBankAccountMapper,
    private val requestBankAccountInfoPort: RequestBankAccountInfoPort,
    private val getMembershipPort: GetMembershipPort
) : RegisterBankAccountUseCase {

    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount? {
        // 은행 계좌를 등록해야하는 서비스

        // (멤버 서비스도 확인?) 여기서는 skip
        val membershipStatus = getMembershipPort.getMembership(command.membershipId!!)
        if (!membershipStatus.isValid) {
            return null
        }

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지(정상인지) 확인한다
        // 외부의 은행에 이 계좌가 정상인지? 확인을 해야해요.
        // Biz Logic -> External System
        // Port -> Adapter -> External System
        // Port
        // 실제 외부의 은행계좌를 정보를 Get
        val accountInfo = requestBankAccountInfoPort.getBankAccountInfo(GetBankAccountRequest(command.bankName!!, command.bankAccountNumber!!))
        if (!accountInfo.isValid) {
            return null
        }

        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
        // 2-1. 등록가능하지 않은 계좌라면. 에러를 리턴
        val membershipJpaEntity = registerBankAccountPort.createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId(command.membershipId),
            RegisteredBankAccount.BankName(command.bankName),
            RegisteredBankAccount.BankAccountNumber(command.bankAccountNumber),
            RegisteredBankAccount.LinkedStatusIsValid(command.linkedStatusIsValid!!),
        )

        return registeredBankAccountMapper.mapToDomainEntity(membershipJpaEntity)
    }
}
