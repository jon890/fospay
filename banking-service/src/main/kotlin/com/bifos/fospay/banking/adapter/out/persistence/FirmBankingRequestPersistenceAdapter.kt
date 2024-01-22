package com.bifos.fospay.banking.adapter.out.persistence

import com.bifos.fospay.banking.application.port.out.RequestFirmBankingPort
import com.bifos.fospay.common.PersistenceAdapter
import java.util.*

@PersistenceAdapter
class FirmBankingRequestPersistenceAdapter(
    private val firmBankingRequestRepository: SpringDataRequestFirmBankingRepository
) : RequestFirmBankingPort {

    override fun createFirmBankingRequest(
        fromBankName: String,
        fromBankAccountNumber: String,
        toBankName: String,
        toBankAccountNumber: String,
        moneyAmount: Int,
        firmBankingStatus: String,
        uuid: UUID,
        aggregateIdentifier: String?
    ): FirmBankingRequestJpaEntity {
        return firmBankingRequestRepository.save(
            FirmBankingRequestJpaEntity(
                null,
                fromBankName,
                fromBankAccountNumber,
                toBankName,
                toBankAccountNumber,
                moneyAmount,
                firmBankingStatus,
                uuid,
                aggregateIdentifier
            )
        )
    }

    override fun modifyFirmBankingRequest(entity: FirmBankingRequestJpaEntity): FirmBankingRequestJpaEntity {
        return firmBankingRequestRepository.save(entity)
    }

    override fun getFirmBankingRequest(aggregateIdentifier: String): FirmBankingRequestJpaEntity? {
        return firmBankingRequestRepository.findByAggregateIdentifier(aggregateIdentifier)
    }
}
