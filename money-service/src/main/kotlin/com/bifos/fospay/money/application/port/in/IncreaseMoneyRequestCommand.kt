package com.bifos.fospay.money.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class IncreaseMoneyRequestCommand(
    @get:NotNull
    val targetMembershipId: Long?,

    @get:NotNull
    @get:Positive
    val amount: Int?

) : SelfValidating<IncreaseMoneyRequestCommand>() {

    init {
        validateSelf()
    }
}
