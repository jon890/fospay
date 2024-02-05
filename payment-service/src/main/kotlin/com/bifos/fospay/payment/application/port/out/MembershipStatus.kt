package com.bifos.fospay.payment.application.port.out

data class MembershipStatus(
    val membershipId: Long,
    val isValid: Boolean
)