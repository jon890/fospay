package com.bifos.fospay.common.event

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RollbackFirmBankingRequestCommand(
    val rollbackFirmBankingId: String,

    @TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val rechargingRequestId: String,

    val membershipId: Long,

    val bankName: String,

    val bankAccountNumber: String,

    val amount: Int
) {
}