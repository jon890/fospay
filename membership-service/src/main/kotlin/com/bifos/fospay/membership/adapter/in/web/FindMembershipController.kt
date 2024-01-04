package com.bifos.fospay.membership.adapter.`in`.web

import com.bifos.fospay.membership.application.port.`in`.FindMembershipCommand
import com.bifos.fospay.membership.application.port.`in`.FindMembershipUseCase
import com.bifos.fospay.membership.domain.Membership
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FindMembershipController(
    private val findMembershipUseCase: FindMembershipUseCase
) {

    @GetMapping("/membership/{membershipId}")
    fun findMembershipById(@PathVariable membershipId: Long): ResponseEntity<Membership?> {
        val command = FindMembershipCommand(membershipId)
        val membership = findMembershipUseCase.findMembership(command)
        return ResponseEntity.ok().body(membership)
    }
}