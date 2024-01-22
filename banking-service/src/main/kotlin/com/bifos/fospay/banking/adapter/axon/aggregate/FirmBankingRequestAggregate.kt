package com.bifos.fospay.banking.adapter.axon.aggregate

import com.bifos.fospay.banking.adapter.axon.command.AxonCreateFirmBankingRequestCommand
import com.bifos.fospay.banking.adapter.axon.command.AxonUpdateFirmBankingRequestCommand
import com.bifos.fospay.banking.adapter.axon.event.AxonFirmBankingRequestEvent
import com.bifos.fospay.banking.adapter.axon.event.AxonFirmBankingUpdateEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.*

@Aggregate
class FirmBankingRequestAggregate() {
    @AggregateIdentifier
    var id: String? = null

    var fromBankName: String? = null

    var fromBankAccountNumber: String? = null

    var toBankName: String? = null

    var toBankAccountNumber: String? = null

    var moneyAmount: Int? = null

    var firmBankingStatus: Int? = null

    private val logger = LoggerFactory.getLogger(this::class.java)

    @CommandHandler
    constructor(command: AxonCreateFirmBankingRequestCommand) : this() {
        logger.info("CreateFirmBankingRequestCommand Handler")

        AggregateLifecycle.apply(
            AxonFirmBankingRequestEvent(
                fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber, moneyAmount
            )
        )
    }

    @CommandHandler
    fun handle(command : AxonUpdateFirmBankingRequestCommand) : String {
        logger.info("UpdateFirmBankingRequestCommand Handler")

        id = command.aggregateIdentifier
        AggregateLifecycle.apply(
            AxonFirmBankingUpdateEvent(command.status!!)
        )

        return id!!
    }

    @EventSourcingHandler
    fun on(event: AxonFirmBankingRequestEvent) {
        logger.info("CreateFirmBankingRequestCommand Handler")

        id = UUID.randomUUID().toString()
        fromBankName = event.fromBankName
        fromBankAccountNumber = event.fromBankAccountNumber
        toBankName = event.toBankName
        toBankAccountNumber = event.toBankAccountNumber
        moneyAmount = event.moneyAmount
    }

    @EventSourcingHandler
    fun on(event: AxonFirmBankingUpdateEvent) {
        logger.info("UpdateFirmBankingRequestCommand Handler")

        firmBankingStatus = event.firmBankingStatus
    }
}