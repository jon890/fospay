package com.bifos.fospay.remittance.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotBlank

data class FindRemittanceCommand(
    @get:NotBlank
    val membershipId: Long?
) : SelfValidating<FindRemittanceCommand>() {

    init {
        validateSelf()
    }
}
