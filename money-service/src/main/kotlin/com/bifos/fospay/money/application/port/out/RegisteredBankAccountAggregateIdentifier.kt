package com.bifos.fospay.money.application.port.out

data class RegisteredBankAccountAggregateIdentifier(
    val id: Long,
    val aggregateIdentifier: String,
    val membershipId: Long,
    val bankName: String,
    val bankAccountNumber: String
) {
}