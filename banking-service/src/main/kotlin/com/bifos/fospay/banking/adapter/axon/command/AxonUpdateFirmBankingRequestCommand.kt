package com.bifos.fospay.banking.adapter.axon.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AxonUpdateFirmBankingRequestCommand(
    @TargetAggregateIdentifier
    var aggregateIdentifier: String?,

    var status: Int?
) {
}