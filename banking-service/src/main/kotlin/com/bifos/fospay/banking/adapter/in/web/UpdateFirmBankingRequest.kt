package com.bifos.fospay.banking.adapter.`in`.web

data class UpdateFirmBankingRequest(
    val aggregateIdentifier: String?,
    val status: Int?
)