package com.bifos.fospay.money.application.service

import com.bifos.fospay.common.CountDownLatchManager
import com.bifos.fospay.common.RechargingMoneyTask
import com.bifos.fospay.common.SubTask
import com.bifos.fospay.money.adapter.axon.command.AxonCreateMemberMoneyCommand
import com.bifos.fospay.money.adapter.axon.command.AxonIncreaseMoneyCommand
import com.bifos.fospay.money.adapter.out.persistence.MoneyChangingRequestMapper
import com.bifos.fospay.money.application.port.`in`.CreateMemberMoneyCommand
import com.bifos.fospay.money.application.port.`in`.CreateMemberMoneyUseCase
import com.bifos.fospay.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.bifos.fospay.money.application.port.`in`.IncreaseMoneyRequestUseCase
import com.bifos.fospay.money.application.port.out.*
import com.bifos.fospay.money.domain.MoneyChangingRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class IncreaseMoneyRequestService(
    private val increaseMoneyPort: IncreaseMoneyPort,
    private val moneyChangingRequestMapper: MoneyChangingRequestMapper,
    private val getMembershipPort: GetMembershipPort,
    private val sendRechargingMoneyTaskPort: SendRechargingMoneyTaskPort,
    private val countDownLatchManager: CountDownLatchManager,
    private val commandGateway: CommandGateway,
    private val createMemberMoneyPort: CreateMemberMoneyPort,
    private val getMemberMoneyPort: GetMemberMoneyPort
) : IncreaseMoneyRequestUseCase, CreateMemberMoneyUseCase {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {
        // 머니의 충전, 증액이라는 과정
        val membershipStatus = getMembershipPort.getMembership(command.targetMembershipId!!)
        if (!membershipStatus.isValid) {
            throw RuntimeException("유저 상태가 올바르지 않습니다 ===> ${command.targetMembershipId}")
        }

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)

        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)

        // 4. 증액을 위한 기록, 요청 상태로 MoneyChangingRequest를 생성한다.

        // 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 => fospay 법인 계좌) (뱅킹)

        // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴

        // 성공 시에 멤버의 MeberMoney 값 증액이 필요해요
        val memberMoney = increaseMoneyPort.increaseMoney(command.targetMembershipId, balance = command.amount!!)

        // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        val entity = increaseMoneyPort.createMoneyChangingRequest(
            targetMembershipId = command.targetMembershipId,
            moneyChangingType = 0,
            changingMoneyAmount = command.amount,
            moneyChangingStatus = 1,
            uuid = UUID.randomUUID()
        )

        return moneyChangingRequestMapper.mapToDomainEntity(entity)
    }

    override fun increaseMoneyAsync(command: IncreaseMoneyRequestCommand): MoneyChangingRequest? {
        // assert
        command.targetMembershipId!!
        command.amount!!

        // 1. Subtask, Task
        val validMemberTask = SubTask(
            membershipId = command.targetMembershipId,
            subTaskName = "validMemberTask: 멤버십 유효성 검사",
            taskType = "membership",
            status = "ready"
        )

        // Banking Sub task
        // Banking Account Validation
        // Amount money Firmbanking --> 무조건 ok 받았다고 가정.
        val validBankingAccountTask = SubTask(
            membershipId = command.targetMembershipId,
            subTaskName = "validBankingAccountTask : 뱅킹 계좌 유효성 검사",
            taskType = "banking",
            status = "ready"
        )

        val task = RechargingMoneyTask(
            taskId = UUID.randomUUID().toString(),
            taskName = "Increase Money Task / 머니 충전 Task",
            subTasks = listOf(validMemberTask, validBankingAccountTask),
            membershipId = command.targetMembershipId,
            toBankName = "fosbank", // 법인 계좌
            toBankAccountNumber = "111-222-333",
            moneyAmount = command.amount
        )

        // 2. Kafka Cluster Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task)

        // 3. Wait
        countDownLatchManager.addCountDownLatch(task.taskId)
        countDownLatchManager.getCountDownLatch(task.taskId)?.await()

        // 3-1. task-consumer
        // 등록된 sub-task, status 모두 ok -> task 결과를 Produce

        // 4. Task Result Consume
        val result = countDownLatchManager.getDataForKey(task.taskId)
        when (result) {
            "success" -> {
                // 4-1 Consume ok
            }

            "failed" -> {
                // Consume fail
                return null
            }

            else -> {
                return null
            }
        }

        // 5. Consume ok, Logic
        val memberMoney = increaseMoneyPort.increaseMoney(command.targetMembershipId, balance = command.amount)

        // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        val entity = increaseMoneyPort.createMoneyChangingRequest(
            targetMembershipId = command.targetMembershipId,
            moneyChangingType = 0,
            changingMoneyAmount = command.amount,
            moneyChangingStatus = 1,
            uuid = UUID.randomUUID()
        )

        return moneyChangingRequestMapper.mapToDomainEntity(entity)
    }

    override fun createMemberMoney(command: CreateMemberMoneyCommand) {
        val axonCommand = AxonCreateMemberMoneyCommand(command.membershipId)
        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                logger.error("Error occurred", throwable)
                throw RuntimeException(throwable)
            } else {
                logger.info("result: {}", result)
                createMemberMoneyPort.createMemberMoney(command.membershipId!!, result)
            }
        }
    }

    override fun increaseMoneyByEvent(command: IncreaseMoneyRequestCommand) {
        val entity = getMemberMoneyPort.getMemberMoney(command.targetMembershipId!!)

        val axonCommand = AxonIncreaseMoneyCommand(entity.aggregateIdentifier, entity.membershipId, command.amount)
        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                logger.error("Error occurred", throwable)
                throw RuntimeException(throwable)
            } else {
                logger.info("result: {}", result)
                increaseMoneyPort.increaseMoney(command.targetMembershipId, command.amount!!)
            }
        }
    }
}
