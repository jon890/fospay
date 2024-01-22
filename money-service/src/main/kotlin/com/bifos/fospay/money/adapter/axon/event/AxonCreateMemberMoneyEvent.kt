package com.bifos.fospay.money.adapter.axon.event

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class AxonCreateMemberMoneyEvent(
    @get:NotNull
    val membershipId: Long?

) : SelfValidating<AxonCreateMemberMoneyEvent>() {

    init {
        validateSelf()
    }
}
