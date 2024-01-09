package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterMembershipCommand(
    @get:NotNull
    @get:NotBlank
    val name: String?,

    @get:NotNull
    @get:NotBlank
    val email: String?,

    @get:NotNull
    @get:NotBlank
    val address: String?,

    @AssertTrue
    val isValid: Boolean = true,

    @get:NotNull
    val isCorp: Boolean?
) : SelfValidating<RegisterMembershipCommand>() {

    init {
        validateSelf()
    }
}
