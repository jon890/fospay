package com.bifos.fospay.common.event

data class RequestFirmBankingFinishedEvent(
    val requestFirmBankingId: String,
    val rechargingRequestId: String,
    val membershipId: Long,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val moneyAmount: Int,
    val status: Int,
    val requestFirmBankingAggregateIdentifier: String
) {
}