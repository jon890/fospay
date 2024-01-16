package com.bifos.fospay.remittance.application.port.out.membership

data class MembershipStatus(
    val membershipId: Long,
    val isValid: Boolean
)