package com.bifos.fospay.money.adapter.axon.event

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class AxonIncreaseMoneyEvent(
    @get:NotNull
    val aggregateIdentifier: String?,

    @get:NotNull
    val targetMembershipId: Long?,

    @get:NotNull
    val amount: Int?

) : SelfValidating<AxonIncreaseMoneyEvent>() {

    init {
        validateSelf()
    }
}
