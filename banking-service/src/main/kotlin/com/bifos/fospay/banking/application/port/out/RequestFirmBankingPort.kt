package com.bifos.fospay.banking.application.port.out

import com.bifos.fospay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity
import java.util.*

interface RequestFirmBankingPort {

    fun createFirmBankingRequest(
        fromBankName: String,
        fromBankAccountNumber: String,
        toBankName: String,
        toBankAccountNumber: String,
        moneyAmount: Int,
        firmBankingStatus: String,
        uuid: UUID
    ): FirmBankingRequestJpaEntity

    fun modifyFirmBankingRequest(
        entity: FirmBankingRequestJpaEntity
    ): FirmBankingRequestJpaEntity
}