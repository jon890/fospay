package com.bifos.fospay.payment.domain

import java.time.LocalDateTime

class Payment private constructor(
    val id: Long,
    val requestMembershipId: Long,
    val requestPrice: String,
    val franchiseId: Long,
    val franchiseFeeRate: Double,
    val paymentStatus: Int,
    val approvedAt: LocalDateTime
) {
    companion object {
        fun generate(
            id: Long,
            requestMembershipId: Long,
            requestPrice: String,
            franchiseId: Long,
            franchiseFeeRate: Double,
            paymentStatus: Int,
            approvedAt: LocalDateTime
        ): Payment {
            return Payment(
                id, requestMembershipId, requestPrice, franchiseId, franchiseFeeRate, paymentStatus, approvedAt
            )
        }
    }
}