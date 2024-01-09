package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.banking.domain.RegisteredBankAccount

@UseCase
interface RegisterBankAccountUseCase {

    fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount?
}
