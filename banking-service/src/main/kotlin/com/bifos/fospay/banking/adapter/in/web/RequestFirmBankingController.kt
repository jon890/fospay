package com.bifos.fospay.banking.adapter.`in`.web

import com.bifos.fospay.banking.application.port.`in`.RequestFirmBankingCommand
import com.bifos.fospay.banking.application.port.`in`.RequestFirmBankingUseCase
import com.bifos.fospay.banking.application.port.`in`.UpdateFirmBankingCommand
import com.bifos.fospay.banking.application.port.`in`.UpdateFirmBankingUseCase
import com.bifos.fospay.common.WebAdapter
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestFirmBankingController(
    private val requestFirmBankingUseCase: RequestFirmBankingUseCase,
    private val updateFirmBankingUseCase: UpdateFirmBankingUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/banking/firmbanking/request")
    fun requestFirmBanking(@RequestBody requestBody: RequestFirmBankingRequest): ResponseEntity<*> {
        val command = RequestFirmBankingCommand(
            fromBankName = requestBody.fromBankName,
            fromBankAccountNumber = requestBody.fromBankAccountNumber,
            toBankName = requestBody.toBankName,
            toBankAccountNumber = requestBody.toBankAccountNumber,
            moneyAmount = requestBody.moneyAmount
        )

        val firmBankingRequest = requestFirmBankingUseCase.requestFirmBanking(command)
        return ResponseEntity.ok().body(firmBankingRequest)
    }

    @PostMapping("/banking/firmbanking/request-eda")
    fun requestFirmBankingByEvent(@RequestBody requestBody: RequestFirmBankingRequest): ResponseEntity<*> {
        val command = RequestFirmBankingCommand(
            fromBankName = requestBody.fromBankName,
            fromBankAccountNumber = requestBody.fromBankAccountNumber,
            toBankName = requestBody.toBankName,
            toBankAccountNumber = requestBody.toBankAccountNumber,
            moneyAmount = requestBody.moneyAmount
        )

        val firmBankingRequest = requestFirmBankingUseCase.requestFirmBankingByEvent(command)
        return ResponseEntity.ok().body(firmBankingRequest)
    }

    @PutMapping("/banking/firmbanking/update-eda")
    fun updateFirmBankingByEvent(@RequestBody requestBody: UpdateFirmBankingRequest): ResponseEntity<*> {
        val command = UpdateFirmBankingCommand(
            aggregateIdentifier = requestBody.aggregateIdentifier,
            status = requestBody.status
        )

        val firmBankingRequest = updateFirmBankingUseCase.requestFirmBankingByEvent(command)
        return ResponseEntity.ok().body(firmBankingRequest)
    }
}