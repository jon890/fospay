package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterBankAccountCommand(
    @get:NotNull
    val membershipId: Long?,

    @get:NotNull
    @get:NotBlank
    val bankName: String?,

    @get:NotNull
    @get:NotBlank
    val bankAccountNumber: String?,

    @get:NotNull
    val linkedStatusIsValid: Boolean?

) : SelfValidating<RegisterBankAccountCommand>() {

    init {
        validateSelf()
    }
}
