package com.bifos.fospay.remittance.application.port.`in`

import com.bifos.fospay.common.SelfValidating
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class RequestRemittanceCommand(
    @get:NotBlank
    val fromMembershipId: Long?,

//    @get:NotBlank
     val toMembershipId: Long?, // 외부 은행 혹은 유저에게 송금 가능

    @get:NotBlank
    val toBankName: String?,

    @get:NotBlank
    val toBankAccountNumber: String?,

    @get:NotNull
    @get:Positive
    val amount: Int?,

    @get:NotNull
    @get:Positive // TODO enum
    val remittanceType: Int?

) : SelfValidating<RequestRemittanceCommand>() {

    init {
        validateSelf()
    }
}
