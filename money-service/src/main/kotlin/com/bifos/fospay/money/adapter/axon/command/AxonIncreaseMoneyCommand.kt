package com.bifos.fospay.money.adapter.axon.command

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AxonIncreaseMoneyCommand(
    @get:NotNull
    @get:TargetAggregateIdentifier
    val aggregateIdentifier: String? = null,

    @get:NotNull
    val targetMembershipId: Long? = null,

    @get:NotNull
    @get:Positive
    val amount: Int? = null
) {
    constructor() : this(null, null, null) {

    }
}
