package com.bifos.fospay.payment.adapter.out.persistence

import com.bifos.fospay.payment.domain.Payment
import org.springframework.stereotype.Component

@Component
class PaymentMapper {

    fun mapToDomainEntity(entity: PaymentJpaEntity): Payment {
        return Payment.generate(
            id = entity.id!!,
            requestMembershipId = entity.membershipId,
            requestPrice = entity.price,
            franchiseId = entity.franchiseId,
            franchiseFeeRate = entity.franchiseFeeRate,
            paymentStatus = entity.paymentStatus,
            approvedAt = entity.createdAt!!
        )
    }
}