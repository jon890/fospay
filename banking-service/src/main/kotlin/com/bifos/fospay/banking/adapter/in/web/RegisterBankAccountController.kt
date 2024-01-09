package com.bifos.fospay.banking.adapter.`in`.web

import com.bifos.fospay.banking.application.port.`in`.RegisterBankAccountCommand
import com.bifos.fospay.banking.application.port.`in`.RegisterBankAccountUseCase
import com.bifos.fospay.common.WebAdapter
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterBankAccountController(
    private val registerMembershipUseCase: RegisterBankAccountUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/banking/account")
    fun registerBankAccount(@RequestBody requestBody: RegisterBankAccountRequest): ResponseEntity<*> {
        val command = RegisterBankAccountCommand(
            membershipId = requestBody.membershipId,
            bankName = requestBody.bankName,
            bankAccountNumber = requestBody.bankAccountNumber,
            linkedStatusIsValid = requestBody.linkedStatusIsValid
        )

        val registeredBankAccount = registerMembershipUseCase.registerBankAccount(command)
            ?: return ResponseEntity.badRequest().body(null)
        return ResponseEntity.ok().body(registeredBankAccount)
    }
}