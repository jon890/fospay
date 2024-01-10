package com.bifos.fospay.banking.adapter.out.persistence

import com.bifos.fospay.banking.domain.FirmBankingRequest
import org.springframework.stereotype.Component

@Component
class FirmBankingRequestMapper {

    fun mapToDomainEntity(entity: FirmBankingRequestJpaEntity): FirmBankingRequest {
        return FirmBankingRequest.generate(
            id = entity.id!!,
            fromBankName = entity.fromBankName,
            fromBankAccount = entity.fromBankAccountNumber,
            toBankName = entity.toBankName,
            toBankAccount = entity.toBankAccountNumber,
            moneyAmount = entity.moneyAmount,
            firmBankingStatus = entity.firmBankingStatus,
            uuid = entity.uuid
        )
    }
}