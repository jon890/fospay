package com.bifos.fospay.money.application.port.out

data class MembershipStatus(
    val membershipId: Long,
    val isValid: Boolean
)