package com.bifos.fospay.banking.adapter.`in`.web

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterBankAccountRequest(
    val membershipId: Long,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean
)
