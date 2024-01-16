package com.bifos.fospay.remittance.application.port.`in`

import com.bifos.fospay.remittance.domain.RemittanceRequest

interface FindRemittanceUseCase {

    fun findRemittanceHistory(command : FindRemittanceCommand) : List<RemittanceRequest>
}