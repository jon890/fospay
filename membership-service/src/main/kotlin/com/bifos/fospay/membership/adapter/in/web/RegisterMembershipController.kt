package com.bifos.fospay.membership.adapter.`in`.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterMembershipController {

    @GetMapping("/test")
    fun test(): String {
        return "Hello world!"
    }
}