package com.bifos.fospay.banking.application.port.out

import com.bifos.fospay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity
import com.bifos.fospay.banking.application.port.`in`.GetRegisteredBankAccountCommand

interface GetRegisteredBankAccountPort {
    fun getRegisteredBankAccount(command: GetRegisteredBankAccountCommand): RegisteredBankAccountJpaEntity?
}
