package com.bifos.fospay.membership.adapter.`in`.web

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterMembershipRequest(
    val name: String?,
    val address: String?,
    val email: String?,
    @JsonProperty("corp")
    val isCorp: Boolean?,
)
