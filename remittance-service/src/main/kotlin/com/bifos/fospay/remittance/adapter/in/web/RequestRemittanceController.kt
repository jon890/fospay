package com.bifos.fospay.remittance.adapter.`in`.web

import com.bifos.fospay.common.WebAdapter
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceCommand
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestRemittanceController(
    private val requestRemittanceUseCase: RequestRemittanceUseCase,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/remittance/request")
    fun requestRemittance(@RequestBody requestBody: RequestRemittanceRequest): ResponseEntity<*> {
        val command = RequestRemittanceCommand(
            fromMembershipId = requestBody.fromMembershipId,
            toMembershipId = requestBody.toMembershipId,
            toBankName = requestBody.toBankName,
            toBankAccountNumber = requestBody.toBankAccountNumber,
            remittanceType = requestBody.remittanceType,
            amount = requestBody.amount
        )

        val requestRemittance = requestRemittanceUseCase.requestRemittance(command)

        return ResponseEntity.ok()
            .body(requestRemittance)
    }
}
