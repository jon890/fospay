package com.bifos.fospay.banking.adapter.axon.event

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class AxonFirmBankingRequestEvent(
    @get:NotNull
    val fromBankName: String?,

    @get:NotNull
    val fromBankAccountNumber: String?,

    @get:NotNull
    val toBankName: String?,

    @get:NotNull
    val toBankAccountNumber: String?,

    @get:NotNull
    val moneyAmount: Int?

) : SelfValidating<AxonFirmBankingRequestEvent>() {
}