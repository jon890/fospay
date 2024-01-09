package com.bifos.fospay.membership.adapter.`in`.web

import com.bifos.fospay.membership.application.port.`in`.ModifyMembershipCommand
import com.bifos.fospay.membership.application.port.`in`.ModifyMembershipUseCase
import com.bifos.fospay.membership.domain.Membership
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyMembershipController(
    private val modifyMembershipUseCase: ModifyMembershipUseCase
) {

    @PatchMapping("/membership/{membershipId}")
    fun modifyMembershipByMembershipId(
        @PathVariable membershipId: Long,
        @RequestBody requestDto: ModifyMembershipRequest
    ): ResponseEntity<Membership?> {
        val command = ModifyMembershipCommand(
            id = membershipId,
            address = requestDto.address,
            name = requestDto.name,
            email = requestDto.email,
            isCorp = requestDto.isCorp,
            isValid = requestDto.isValid
        )
        val membership = modifyMembershipUseCase.modifyMembership(command)
        return ResponseEntity.ok().body(membership)
    }
}