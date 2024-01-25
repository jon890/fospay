package com.bifos.fospay.money.adapter.out.service

data class RegisteredBankAccount(
    val id: Long,

    val membershipId: Long,

    val bankName: String,

    val bankAccountNumber: String,

    val linkedStatusIsValid: Boolean,

    val aggregateIdentifier: String
) {
}