package com.bifos.fospay.banking.adapter.`in`.web

import com.bifos.fospay.banking.application.port.`in`.GetRegisteredBankAccountCommand
import com.bifos.fospay.banking.application.port.`in`.GetRegisteredBankAccountUseCase
import com.bifos.fospay.banking.domain.RegisteredBankAccount
import com.bifos.fospay.common.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class GetRegisteredBankAccountController(
    private val getRegisteredBankAccountUseCase: GetRegisteredBankAccountUseCase
) {

    @GetMapping("/banking/account/{membershipId}")
    fun getRegisteredBankAccount(@PathVariable membershipId: Long): RegisteredBankAccount? {
        val command = GetRegisteredBankAccountCommand(membershipId)
        return getRegisteredBankAccountUseCase.getRegisteredBankAccount(command)
    }
}