package com.bifos.fospay.banking.adapter.axon.command

data class AxonCreateFirmBankingRequestCommand(
    var fromBankName: String? = null,

    var fromBankAccountNumber: String? = null,

    var toBankName: String? = null,

    var toBankAccountNumber: String? = null,

    var moneyAmount: Int? = null,
) {

    constructor() : this(null, null, null, null, null) {

    }
}