package com.bifos.fospay.membership.adapter.`in`.web

import common.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterMembershipController {

    @GetMapping("/test")
    fun test(): String {
        return "Hello world!"
    }
}