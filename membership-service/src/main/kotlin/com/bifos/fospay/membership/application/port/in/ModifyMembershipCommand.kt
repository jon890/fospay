package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.common.SelfValidating
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
