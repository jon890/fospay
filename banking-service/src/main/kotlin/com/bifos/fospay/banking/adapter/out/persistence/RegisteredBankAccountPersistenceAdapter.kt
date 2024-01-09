package com.bifos.fospay.banking.adapter.out.persistence

import com.bifos.fospay.banking.application.port.out.RegisterBankAccountPort
import com.bifos.fospay.banking.domain.RegisteredBankAccount
import com.bifos.fospay.common.PersistenceAdapter

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    private val registeredBankAccountRepository: SpringDataRegisteredBankAccountRepository
) : RegisterBankAccountPort {

    override fun createRegisteredBankAccount(
        membershipId: RegisteredBankAccount.MembershipId,
        bankName: RegisteredBankAccount.BankName,
        bankAccountNumber: RegisteredBankAccount.BankAccountNumber,
        linkedStatusIsValid: RegisteredBankAccount.LinkedStatusIsValid
    ): RegisteredBankAccountJpaEntity {
        return registeredBankAccountRepository.save(
            RegisteredBankAccountJpaEntity(
                membershipId = membershipId.membershipId,
                bankName = bankName.bankName,
                bankAccountNumber = bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid = linkedStatusIsValid.linkedStatusIsValid
            )
        )
    }
}
