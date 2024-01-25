package com.bifos.fospay.banking.adapter.axon.aggregate

import com.bifos.fospay.banking.adapter.axon.command.AxonCreateFirmBankingRequestCommand
import com.bifos.fospay.banking.adapter.axon.command.AxonUpdateFirmBankingRequestCommand
import com.bifos.fospay.banking.adapter.axon.event.AxonFirmBankingRequestEvent
import com.bifos.fospay.banking.adapter.axon.event.AxonFirmBankingUpdateEvent
import com.bifos.fospay.banking.adapter.out.external.bank.ExternalFirmBankingRequest
import com.bifos.fospay.banking.application.port.out.RequestExternalFirmBankingPort
import com.bifos.fospay.banking.application.port.out.RequestFirmBankingPort
import com.bifos.fospay.common.event.RequestFirmBankingCommand
import com.bifos.fospay.common.event.RequestFirmBankingFinishedEvent
import com.bifos.fospay.common.event.RollbackFirmBankingFinishedEvent
import com.bifos.fospay.common.event.RollbackFirmBankingRequestCommand
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
    fun handle(command: AxonUpdateFirmBankingRequestCommand): String {
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

    @CommandHandler
    constructor(
        command: RequestFirmBankingCommand,
        firmBankingPort: RequestFirmBankingPort,
        requestExternalFirmBankingPort: RequestExternalFirmBankingPort
    ) : this() {
        logger.info("RequestFirmBankingCommand Handler")
        id = command.aggregateIdentifier

        // 펌뱅킹 수행 기록
        firmBankingPort.createFirmBankingRequest(
            command.fromBankName,
            command.fromBankAccountNumber,
            command.toBankName,
            command.toBankAccountNumber,
            command.moneyAmount,
            "0",
            UUID.fromString(command.requestFirmBankingId),
            command.aggregateIdentifier
        )

        val firmBankingResult = requestExternalFirmBankingPort.requestExternalFirmBanking(
            ExternalFirmBankingRequest(
                command.fromBankName,
                command.fromBankAccountNumber,
                command.toBankName,
                command.toBankAccountNumber,
                command.moneyAmount
            )
        )

        val resultCode = firmBankingResult.resultCode

        AggregateLifecycle.apply(
            RequestFirmBankingFinishedEvent(
                command.requestFirmBankingId,
                command.rechargeRequestId,
                command.membershipId,
                command.fromBankName,
                command.fromBankAccountNumber,
                command.moneyAmount,
                resultCode,
                command.aggregateIdentifier
            )
        )
    }

    @CommandHandler
    constructor(command : RollbackFirmBankingRequestCommand, firmBankingPort: RequestFirmBankingPort, externalFirmBankingPort: RequestExternalFirmBankingPort) : this() {
        id = UUID.randomUUID().toString()

        // rollback 수행
        firmBankingPort.createFirmBankingRequest(
            "fosbank",
            "1234567890",
            command.bankName,
            command.bankAccountNumber,
            command.amount,
            "0",
            UUID.fromString(id),
            id
        )

        val result = externalFirmBankingPort.requestExternalFirmBanking(
            ExternalFirmBankingRequest(
                "fosbank",
                "1234567890",
                command.bankName,
                command.bankAccountNumber,
                command.amount,
            )
        )

        val success = result.resultCode == 0

        AggregateLifecycle.apply(
            RollbackFirmBankingFinishedEvent(
                command.rollbackFirmBankingId,
                command.membershipId,
                id!!
            )
        )
    }
}