package com.bifos.fospay.money.adapter.`in`.web

import com.bifos.fospay.common.WebAdapter
import com.bifos.fospay.money.application.port.`in`.CreateMemberMoneyCommand
import com.bifos.fospay.money.application.port.`in`.CreateMemberMoneyUseCase
import com.bifos.fospay.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.bifos.fospay.money.application.port.`in`.IncreaseMoneyRequestUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestMoneyChangingController(
    private val increaseMoneyRequestUseCase: IncreaseMoneyRequestUseCase,
    private val moneyChangingResultDetailMapper: MoneyChangingResultDetailMapper,
    private val createMemberMoneyUseCase: CreateMemberMoneyUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/money/increase")
    fun increaseMoneyChangingRequest(@RequestBody requestBody: IncreaseMoneyChangingRequest): ResponseEntity<*> {
        val command = IncreaseMoneyRequestCommand(
            targetMembershipId = requestBody.targetMembershipId,
            amount = requestBody.amount
        )

        val moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoney(command)

        return ResponseEntity.ok()
            .body(moneyChangingResultDetailMapper.mapToMoneyChangingResultDetail(moneyChangingRequest))
    }

    @PostMapping("/money/increase-async")
    fun increaseMoneyChangingRequestAsync(@RequestBody requestBody: IncreaseMoneyChangingRequest): ResponseEntity<*> {
        val command = IncreaseMoneyRequestCommand(
            targetMembershipId = requestBody.targetMembershipId,
            amount = requestBody.amount
        )

        val moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyAsync(command)

        return ResponseEntity.ok()
            .body(moneyChangingRequest?.let { moneyChangingResultDetailMapper.mapToMoneyChangingResultDetail(it) })
    }

    @PostMapping("/money/increase-eda")
    fun increaseMoneyChangingRequestEda(@RequestBody requestBody: IncreaseMoneyChangingRequest): ResponseEntity<*> {
        val command = IncreaseMoneyRequestCommand(
            targetMembershipId = requestBody.targetMembershipId,
            amount = requestBody.amount
        )

        val moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyByEvent(command)

        return ResponseEntity.ok().body(null)
    }

//    @PostMapping("/money/decrease")
//    fun decreaseMoneyChangingRequest(@RequestBody requestBody: DecreaseMoneyChangingRequest): ResponseEntity<*> {
//        val command = RegisterBankAccountCommand(
//            membershipId = requestBody.membershipId,
//            bankName = requestBody.bankName,
//            bankAccountNumber = requestBody.bankAccountNumber,
//            linkedStatusIsValid = requestBody.linkedStatusIsValid
//        )

//        return ResponseEntity.ok().body(moneyChangingResultDetailMapper.mapToMoneyChangingResultDetail(moneyChangingRequest))
//    }

    @PostMapping("/money/create-member-money")
    fun createMemberMoney(@RequestBody requestBody: CreateMemberMoneyRequest) {
        createMemberMoneyUseCase.createMemberMoney(
            CreateMemberMoneyCommand(requestBody.membershipId)
        )
    }
}
