package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RequestFirmBankingCommand(
    @get:NotNull
    @get:NotBlank
    val fromBankName: String?,

    @get:NotNull
    @get:NotBlank
    val fromBankAccountNumber: String?,

    @get:NotNull
    @get:NotBlank
    val toBankName: String?,

    @get:NotNull
    @get:NotBlank
    val toBankAccountNumber: String?,

    @get:NotNull
    val moneyAmount: Int?

) : SelfValidating<RegisterBankAccountCommand>() {

    init {
        validateSelf()
    }
}
