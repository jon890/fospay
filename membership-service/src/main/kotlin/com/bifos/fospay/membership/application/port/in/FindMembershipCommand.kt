package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class FindMembershipCommand(
    @get:NotNull
    val id: Long?
) : SelfValidating<FindMembershipCommand>() {

    init {
        validateSelf()
    }
}
