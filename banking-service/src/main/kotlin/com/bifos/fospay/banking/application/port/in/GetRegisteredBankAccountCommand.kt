package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class GetRegisteredBankAccountCommand(
    @get:NotNull
    val membershipId: Long?
) : SelfValidating<GetRegisteredBankAccountCommand>() {

    init {
        validateSelf()
    }
}
