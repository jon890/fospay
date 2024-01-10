package com.bifos.fospay.banking.adapter.out.external.bank

data class ExternalFirmBankingRequest(
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int
) {

}
