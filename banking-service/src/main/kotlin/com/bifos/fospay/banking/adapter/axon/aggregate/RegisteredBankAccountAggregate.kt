package com.bifos.fospay.banking.adapter.axon.aggregate

import com.bifos.fospay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand
import com.bifos.fospay.banking.adapter.axon.event.CreatedRegisteredBankAccountEvent
import com.bifos.fospay.banking.adapter.out.external.bank.GetBankAccountRequest
import com.bifos.fospay.banking.application.port.out.RequestBankAccountInfoPort
import com.bifos.fospay.common.event.CheckRegisteredBankAccountCommand
import com.bifos.fospay.common.event.CheckedRegisteredBankAccountEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.*

@Aggregate
class RegisteredBankAccountAggregate() {
    @AggregateIdentifier
    var id: String? = null

    var membershipId: Long? = null

    var bankName: String? = null

    var bankAccountNumber: String? = null

    private val logger = LoggerFactory.getLogger(this::class.java)

    @CommandHandler
    constructor(command: CreateRegisteredBankAccountCommand) : this() {
        logger.info("CreateRegisteredBankAccountCommand Handler")

        AggregateLifecycle.apply(
            CreatedRegisteredBankAccountEvent(
                command.membershipId,
                command.bankAccountNumber,
                command.bankAccountNumber
            )
        )
    }

    @CommandHandler
    fun handle(command: CheckRegisteredBankAccountCommand, bankAccountInfoPort: RequestBankAccountInfoPort) {
        logger.info("CreateRegisteredBankAccountCommand Handler")

        // Command 를 통해, 이 어그리거트(RegisteredBankAccount) 가 정상인지를 확인해야해요.
        id = command.aggregateIdentifier

        // Check Registered Bank Account
        val bankAccountInfo = bankAccountInfoPort.getBankAccountInfo(
            GetBankAccountRequest(command.bankName, command.bankAccountNumber)
        )

        val firmBankingUUID = UUID.randomUUID().toString()

        // CheckedRegisteredBankAccountEvent
        AggregateLifecycle.apply(
            CheckedRegisteredBankAccountEvent(
                command.rechargeRequestId,
                command.checkRegisteredBankAccountId,
                command.membershipId,
                bankAccountInfo.isValid,
                command.amount,
                firmBankingUUID,
                command.bankName,
                command.bankAccountNumber
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedRegisteredBankAccountEvent) {
        id = UUID.randomUUID().toString()
        membershipId = event.membershipId
        bankName = event.bankName
        bankAccountNumber = event.bankAccountNumber
    }
}