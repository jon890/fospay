package com.bifos.fospay.membership.adapter.`in`.web

data class ModifyMembershipRequest(
    val name: String?,
    val address: String?,
    val email: String?,
    val isCorp: Boolean?,
    val isValid: Boolean?,
)
