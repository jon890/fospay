package com.bifos.fospay.payment.adapter.out.persistence

import com.bifos.fospay.common.PersistenceAdapter
import com.bifos.fospay.payment.application.port.out.CreatePaymentPort
import com.bifos.fospay.payment.domain.Payment

@PersistenceAdapter
class PaymentPersistenceAdapter(
    private val paymentRepository: SpringDataPaymentRepository,
    private val paymentMapper: PaymentMapper,
) : CreatePaymentPort {

    override fun createPayment(
        requestMembershipId: Long,
        requestPrice: String,
        franchiseId: Long,
        franchiseFeeRate: Double,
    ): Payment {
        val entity = paymentRepository.save(
            PaymentJpaEntity(
                membershipId = requestMembershipId,
                price = requestPrice,
                franchiseId = franchiseId,
                franchiseFeeRate = franchiseFeeRate,
                paymentStatus = 0,
            )
        )

        return paymentMapper.mapToDomainEntity(entity)
    }
}
