package com.bifos.fospay.money.adapter.axon.agreegate

import com.bifos.fospay.money.adapter.axon.command.AxonCreateMemberMoneyCommand
import com.bifos.fospay.money.adapter.axon.command.AxonIncreaseMoneyCommand
import com.bifos.fospay.money.adapter.axon.command.RechargingMoneyRequestCreateCommand
import com.bifos.fospay.money.adapter.axon.event.AxonCreateMemberMoneyEvent
import com.bifos.fospay.money.adapter.axon.event.AxonIncreaseMoneyEvent
import com.bifos.fospay.money.adapter.axon.event.RechargingRequestCreatedEvent
import com.bifos.fospay.money.application.port.out.GetRegisteredBankAccountPort
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.*

@Aggregate
class MemberMoneyAggregate {
    @AggregateIdentifier
    var id: String? = null

    var membershipId: Long? = null

    var balance: Int? = null

    private val logger = LoggerFactory.getLogger(this::class.java)

    constructor()

    @CommandHandler
    constructor(command: AxonCreateMemberMoneyCommand) : this() {
        this.membershipId = command.membershipId

        logger.info("MemberMoneyAggregate Command Handler")

        AggregateLifecycle.apply(AxonCreateMemberMoneyEvent(command.membershipId))
    }

    @CommandHandler
    fun handle(command: AxonIncreaseMoneyCommand): String {
        logger.info("IncreaseMemberMoneyCommand handler")
        id = command.aggregateIdentifier

        AggregateLifecycle.apply(AxonIncreaseMoneyEvent(id, command.targetMembershipId, command.amount))

        return id!!
    }

    @EventSourcingHandler
    fun on(event: AxonCreateMemberMoneyEvent) {
        logger.info("MemberMoneyAggregate Sourcing Handler")

        id = UUID.randomUUID().toString()
        membershipId = event.membershipId
        balance = 0
    }

    @EventSourcingHandler
    fun on(event: AxonIncreaseMoneyEvent) {
        logger.info("MemberMoneyAggregate Sourcing Handler")

        id = event.aggregateIdentifier
        membershipId = event.targetMembershipId
        balance = event.amount
    }

    @CommandHandler
    fun startSaga(command: RechargingMoneyRequestCreateCommand, port: GetRegisteredBankAccountPort) {
        logger.info("RechargingMoneyRequestCreateCommand")

        id = command.aggregateIdentifier

        // banking 정보가 필요해요 -> bank svc (get RegisteredBankAccount) 를 위한 Port
        val aggregateIdentifier = port.getRegisteredBankAccount(command.membershipId)

        // Saga Start
        // new RechargingRequestCreatedEvent
        AggregateLifecycle.apply(
            RechargingRequestCreatedEvent(
                registeredBankAccountAggregateIdentifier = aggregateIdentifier.aggregateIdentifier,
                bankName = aggregateIdentifier.bankName,
                bankAccountNumber = aggregateIdentifier.bankAccountNumber,
                requestId = command.rechargingRequestId,
                membershipId = aggregateIdentifier.membershipId,
                amount = command.amount
            )
        )
    }
}
