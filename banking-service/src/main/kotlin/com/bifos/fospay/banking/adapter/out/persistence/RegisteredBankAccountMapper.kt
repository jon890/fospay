package com.bifos.fospay.banking.adapter.out.persistence

import com.bifos.fospay.banking.domain.RegisteredBankAccount
import org.springframework.stereotype.Component

@Component
class RegisteredBankAccountMapper {

    fun mapToDomainEntity(registeredBankAccountJpaEntity: RegisteredBankAccountJpaEntity): RegisteredBankAccount {
        return RegisteredBankAccount.generate(
            registeredBankAccountJpaEntity.id!!,
            registeredBankAccountJpaEntity.membershipId,
            registeredBankAccountJpaEntity.bankName,
            registeredBankAccountJpaEntity.bankAccountNumber,
            registeredBankAccountJpaEntity.linkedStatusIsValid,
            registeredBankAccountJpaEntity.aggregateIdentifier
        )
    }
}