package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.banking.domain.RegisteredBankAccount

interface GetRegisteredBankAccountUseCase {
    fun getRegisteredBankAccount(command : GetRegisteredBankAccountCommand) : RegisteredBankAccount?
}
