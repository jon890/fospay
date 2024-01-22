package com.bifos.fospay.money.adapter.axon.command

import jakarta.validation.constraints.NotNull

data class AxonCreateMemberMoneyCommand(
    @get:NotNull
    val membershipId: Long? = null

) {
    constructor() : this(null) {

    }
}
