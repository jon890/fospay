package com.bifos.fospay.banking.adapter.axon.command

data class CreateRegisteredBankAccountCommand(
    val membershipId: Long,

    val bankName: String,

    val bankAccountNumber: String,
) {
}