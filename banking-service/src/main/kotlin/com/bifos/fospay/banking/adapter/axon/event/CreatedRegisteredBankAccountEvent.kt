package com.bifos.fospay.banking.adapter.axon.event

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class CreatedRegisteredBankAccountEvent(
    @get:NotNull
    val membershipId: Long?,

    @get:NotNull
    val bankName: String?,

    @get:NotNull
    val bankAccountNumber: String?

) : SelfValidating<AxonFirmBankingUpdateEvent>() {
}