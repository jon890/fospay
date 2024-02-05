package com.bifos.fospay.payment.application.port.out

import com.bifos.fospay.payment.domain.Payment
import java.time.LocalDateTime

interface CreatePaymentPort {

    fun createPayment(
        requestMembershipId: Long,
        requestPrice: String,
        franchiseId: Long,
        franchiseFeeRate: Double
    ): Payment
}