package com.bifos.fospay.membership.adapter.`in`.web

import com.bifos.fospay.membership.application.port.`in`.RegisterMembershipCommand
import com.bifos.fospay.membership.application.port.`in`.RegisterMembershipUseCase
import common.WebAdapter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterMembershipController(
    private val registerMembershipUseCase: RegisterMembershipUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/membership/register")
    fun registerMembership(@RequestBody requestBody: RegisterMembershipRequest): String {
        // request~~
        // request => Command
        // Usecase ~~ (request x, command)
        val command = RegisterMembershipCommand(
            name = requestBody.name,
            email = requestBody.email,
            address = requestBody.address,
            isValid = true,
            isCorp = requestBody.isCorp
        )

        registerMembershipUseCase.registerMembership(command)

        return "register"
    }
}