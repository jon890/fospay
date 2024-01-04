package com.bifos.fospay.membership.application.port.`in`

import common.SelfValidating
import jakarta.validation.constraints.NotNull

data class FindMembershipCommand(
    @get:NotNull
    val id: Long?
) : SelfValidating<FindMembershipCommand>() {

    init {
        validateSelf()
    }
}
