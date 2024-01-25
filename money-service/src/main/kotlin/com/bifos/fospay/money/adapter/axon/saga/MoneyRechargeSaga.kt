package com.bifos.fospay.money.adapter.axon.saga

import com.bifos.fospay.common.event.*
import com.bifos.fospay.money.adapter.axon.event.RechargingRequestCreatedEvent
import com.bifos.fospay.money.application.port.out.IncreaseMoneyPort
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.lang.IllegalStateException
import java.util.*

@Saga
class MoneyRechargeSaga(
    @set:Autowired
    @Transient
    var commandGateway: CommandGateway?
) {

    constructor() : this(null) {

    }

    val logger = LoggerFactory.getLogger(this::class.java)

    @StartSaga
    @SagaEventHandler(associationProperty = "requestId")
    fun handle(event: RechargingRequestCreatedEvent) {
        logger.info("RechargingRequestCreatedEvent Start saga")


        val checkRegisteredBankAccountId = UUID.randomUUID().toString()
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId)

        // "충전 요청"이 시작 되었다.

        logger.info("commandGateway is null? {}", commandGateway)

        // 뱅킹의 계좌 등록 여부 확인하기. (RegisteredBankAccount)
        // CheckRegisteredBankAccountCommand -> Check Bank Account
        // -> axon server -> Banking Service
        commandGateway?.send<String>(
            CheckRegisteredBankAccountCommand(
                event.registeredBankAccountAggregateIdentifier,
                event.requestId,
                event.membershipId,
                checkRegisteredBankAccountId,
                event.bankName,
                event.bankAccountNumber,
                event.amount
            )
        )?.whenComplete { result, throwable ->
            if (throwable != null) {
                logger.error("error occurred", throwable)
            } else {
                logger.info("CheckRegisteredBankAccountCommand success")
            }
        }
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    fun handle(event: CheckedRegisteredBankAccountEvent) {
        logger.info("CheckedRegisteredBankAccountEvent saga: {}", event.toString())

        if (event.isChecked) {
            logger.info("CheckedRegisteredBankAccountEvent success")
        } else {
            logger.info("CheckedRegisteredBankAccountEvent failed")
        }

        val requestFirmBankingId = UUID.randomUUID().toString()
        SagaLifecycle.associateWith("requestFirmBankingId", requestFirmBankingId)

        // 송금 요청
        commandGateway?.send<String>(
            RequestFirmBankingCommand(
                requestFirmBankingId,
                event.firmBankingRequestAggregateIdentifier,
                event.rechargingRequestId,
                event.membershipId,
                event.fromBankName,
                event.fromBankAccountNumber,
                "fosbank",
                "123456789",
                event.amount
            )
        )?.whenComplete { result, throwable ->
            if (throwable != null) {
                logger.error("error occurred", throwable)
            } else {
                logger.info("CheckRegisteredBankAccountCommand success")
            }
        }
    }

    @SagaEventHandler(associationProperty = "requestFirmBankingId")
    fun handle(event: RequestFirmBankingFinishedEvent, increaseMoneyPort: IncreaseMoneyPort) {
        logger.info("RequestFirmBankingFinishedEvent saga: {}", event)

        val success = event.status == 0
        if (success) {
            logger.info("RequestFirmBankingFinishedEvent success")
        } else {
            logger.info("RequestFirmBankingFinishedEvent failed")
        }

        try {
//            throw IllegalStateException("test")
            val resultEntity = increaseMoneyPort.increaseMoney(event.membershipId, event.moneyAmount)
            SagaLifecycle.end()
        } catch (e: Exception) {
            // 실패 시, 롤백 이벤트
            val rollbackFirmBankingId = UUID.randomUUID().toString()
            commandGateway?.send<String>(
                RollbackFirmBankingRequestCommand(
                    rollbackFirmBankingId,
                    event.requestFirmBankingAggregateIdentifier,
                    event.rechargingRequestId,
                    membershipId = event.membershipId,
                    bankName = event.fromBankName,
                    bankAccountNumber = event.fromBankAccountNumber,
                    amount = event.moneyAmount
                )
            )?.whenComplete { result, throwable ->
                if (throwable != null) {
                    logger.error("error occurred", throwable)
                } else {
                    logger.info("RollbackFirmBankingRequestCommand success")
                    SagaLifecycle.end()
                }
            }
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmBankingId")
    fun handle(event: RollbackFirmBankingFinishedEvent) {
        logger.info("RollbackFirmBankingFinishedEvent saga : {}", event)
    }
}