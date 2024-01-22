package com.bifos.fospay.money.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class CreateMemberMoneyCommand(
    @get:NotNull
    val membershipId: Long?

) : SelfValidating<CreateMemberMoneyCommand>() {

    init {
        validateSelf()
    }
}
