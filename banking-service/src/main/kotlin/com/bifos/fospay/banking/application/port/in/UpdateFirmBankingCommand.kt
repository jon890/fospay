package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateFirmBankingCommand(
    @get:NotNull
    @get:NotBlank
    val aggregateIdentifier: String?,

    @get:NotNull
    val status: Int?

) : SelfValidating<RegisterBankAccountCommand>() {

    init {
        validateSelf()
    }
}
