package com.bifos.fospay.money.adapter.out.persistence

import com.bifos.fospay.money.domain.MoneyChangingRequest
import org.springframework.stereotype.Component

@Component
class MoneyChangingRequestMapper {

    fun mapToDomainEntity(entity: MoneyChangingRequestJpaEntity): MoneyChangingRequest {
        return MoneyChangingRequest.generate(
            id = entity.id!!,
            targetMembershipId = entity.targetMembershipId,
            changingType = entity.moneyChangingType,
            changingMoneyAmount = entity.changingMoneyAmount,
            changingMoneyStatus = entity.moneyChangingStatus,
            createdAt = entity.createdAt!!,
            uuid = entity.uuid
        )
    }
}