package com.bifos.fospay.banking.application.port.out

import com.bifos.fospay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity
import com.bifos.fospay.banking.domain.RegisteredBankAccount

interface RegisterBankAccountPort {

    fun createRegisteredBankAccount(
        membershipId: Long,
        bankName: String,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean,
        aggregateIdentifier: String
    ): RegisteredBankAccountJpaEntity
}