package com.bifos.fospay.banking.adapter.axon.event

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class AxonFirmBankingUpdateEvent(
    @get:NotNull
    val firmBankingStatus: Int?

) : SelfValidating<AxonFirmBankingUpdateEvent>() {
}