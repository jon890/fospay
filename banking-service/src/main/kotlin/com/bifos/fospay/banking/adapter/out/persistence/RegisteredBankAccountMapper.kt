package com.bifos.fospay.banking.adapter.out.persistence

import com.bifos.fospay.banking.domain.RegisteredBankAccount
import org.springframework.stereotype.Component

@Component
class RegisteredBankAccountMapper {

    fun mapToDomainEntity(registeredBankAccountJpaEntity: RegisteredBankAccountJpaEntity): RegisteredBankAccount {
        return RegisteredBankAccount.generate(
            RegisteredBankAccount.RegisteredBankAccountId(registeredBankAccountJpaEntity.id!!),
            RegisteredBankAccount.MembershipId(registeredBankAccountJpaEntity.membershipId),
            RegisteredBankAccount.BankName(registeredBankAccountJpaEntity.bankName),
            RegisteredBankAccount.BankAccountNumber(registeredBankAccountJpaEntity.bankAccountNumber),
            RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccountJpaEntity.linkedStatusIsValid),
        )
    }
}