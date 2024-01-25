package com.bifos.fospay.common.event

import org.axonframework.modelling.command.TargetAggregateIdentifier

class CheckRegisteredBankAccountCommand(
    @TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val rechargeRequestId: String,

    val membershipId: Long,

    val checkRegisteredBankAccountId: String,

    val bankName: String,

    val bankAccountNumber: String,

    val amount: Int
) {
}