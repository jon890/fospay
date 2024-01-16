package com.bifos.fospay.remittance.application.port.out.banking

data class BankingInfo(
    val bankName: String,
    val bankAccountNumber: String,
    val isValid: Boolean
) {

}
