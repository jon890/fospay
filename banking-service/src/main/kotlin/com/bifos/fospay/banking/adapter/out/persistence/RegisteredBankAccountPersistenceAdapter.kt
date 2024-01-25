package com.bifos.fospay.banking.adapter.out.persistence

import com.bifos.fospay.banking.application.port.`in`.GetRegisteredBankAccountCommand
import com.bifos.fospay.banking.application.port.out.GetRegisteredBankAccountPort
import com.bifos.fospay.banking.application.port.out.RegisterBankAccountPort
import com.bifos.fospay.common.PersistenceAdapter

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    private val registeredBankAccountRepository: SpringDataRegisteredBankAccountRepository
) : RegisterBankAccountPort, GetRegisteredBankAccountPort {

    override fun createRegisteredBankAccount(
        membershipId: Long,
        bankName: String,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean,
        aggregateIdentifier: String
    ): RegisteredBankAccountJpaEntity {
        return registeredBankAccountRepository.save(
            RegisteredBankAccountJpaEntity(
                membershipId = membershipId,
                bankName = bankName,
                bankAccountNumber = bankAccountNumber,
                linkedStatusIsValid = linkedStatusIsValid,
                aggregateIdentifier = aggregateIdentifier
            )
        )
    }

    override fun getRegisteredBankAccount(command: GetRegisteredBankAccountCommand): RegisteredBankAccountJpaEntity? {
        return registeredBankAccountRepository.findByMembershipId(command.membershipId!!)
    }
}
