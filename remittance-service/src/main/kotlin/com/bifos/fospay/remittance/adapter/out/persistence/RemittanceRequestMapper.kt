package com.bifos.fospay.remittance.adapter.out.persistence

import com.bifos.fospay.remittance.domain.RemittanceRequest
import org.springframework.stereotype.Component

@Component
class RemittanceRequestMapper {

    fun mapToDomainEntity(entity: RemittanceRequestJpaEntity): RemittanceRequest {
        return RemittanceRequest.generate(
            requestId = entity.id!!.toString(),
            fromMembershipId = entity.fromMembershipId,
            toBankName = entity.toBankName,
            toBankAccountNumber = entity.toBankAccountNumber,
            remittanceType = entity.remittanceType,
            amount = entity.amount,
            status = entity.remittanceStatus
        )
    }
}