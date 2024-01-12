package com.bifos.fospay.banking.application.port.out

data class MembershipStatus(
    val membershipId: Long,
    val isValid: Boolean
)