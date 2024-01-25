package com.bifos.fospay.common.event

data class CheckedRegisteredBankAccountEvent(
    val rechargingRequestId: String,

    val checkRegisteredBankAccountId: String,

    val membershipId: Long,

    val isChecked: Boolean,

    val amount: Int,

    val firmBankingRequestAggregateIdentifier: String,

    val fromBankName: String,

    val fromBankAccountNumber: String
) {
}