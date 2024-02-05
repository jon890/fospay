package com.bifos.fospay.payment.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class RequestPaymentCommand(
    @NotNull
    val membershipId: Long?,

    @NotNull
    val price: String?,

    @NotNull
    val franchiseId: Long?,

    @NotNull
    val franchiseFeeRate: Double?

) : SelfValidating<RequestPaymentCommand>() {

    init {
        validateSelf()
    }
}
