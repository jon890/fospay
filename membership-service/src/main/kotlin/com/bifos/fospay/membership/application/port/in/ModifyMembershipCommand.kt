package com.bifos.fospay.membership.application.port.`in`

import common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ModifyMembershipCommand(
    @get:NotNull
    val id: Long?,

    val name: String?,
    val email: String?,
    val address: String?,
    val isValid: Boolean?,
    val isCorp: Boolean?
) : SelfValidating<ModifyMembershipCommand>() {

    init {
        validateSelf()
    }
}
