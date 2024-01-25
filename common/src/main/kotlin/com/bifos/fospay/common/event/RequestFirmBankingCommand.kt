package com.bifos.fospay.common.event

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RequestFirmBankingCommand(
    val requestFirmBankingId: String,
    @TargetAggregateIdentifier
    val aggregateIdentifier: String,
    val rechargeRequestId: String,
    val membershipId: Long,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int
) {
}