package com.bifos.fospay.remittance.application.port.out

import com.bifos.fospay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceCommand

interface RequestRemittancePort {

    fun createRemittanceRequestHistory(command: RequestRemittanceCommand): RemittanceRequestJpaEntity

    fun saveRemittanceRequestHistory(entity : RemittanceRequestJpaEntity) : Boolean
}