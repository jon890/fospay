package com.bifos.fospay.remittance.adapter.`in`.web

import com.bifos.fospay.common.WebAdapter
import com.bifos.fospay.remittance.application.port.`in`.FindRemittanceCommand
import com.bifos.fospay.remittance.application.port.`in`.FindRemittanceUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindRemittanceHistoryController(
    private val findRemittanceUseCase: FindRemittanceUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/remittance/{membershipId}")
    fun findRemittanceHistory(@PathVariable membershipId: Long): ResponseEntity<*> {
        val command = FindRemittanceCommand(membershipId)

        val histories = findRemittanceUseCase.findRemittanceHistory(command)

        return ResponseEntity.ok()
            .body(histories)
    }
}
