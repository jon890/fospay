package com.bifos.fospay.common.event

data class RollbackFirmBankingFinishedEvent(
    val rollbackFirmBankingId: String,

    val membershipId: Long,

    val rollbackFirmBankingAggregateIdentifier: String
) {
}