package com.bifos.fospay.remittance.application.port.out

import com.bifos.fospay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity
import com.bifos.fospay.remittance.application.port.`in`.FindRemittanceCommand
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceCommand

interface FindRemittancePort {

    fun findRemittanceHistory(command : FindRemittanceCommand) : List<RemittanceRequestJpaEntity>
}