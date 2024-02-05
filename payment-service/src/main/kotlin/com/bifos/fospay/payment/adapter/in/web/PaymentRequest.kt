package com.bifos.fospay.payment.adapter.`in`.web

data class PaymentRequest(
    val membershipId: Long?,
    val price: String?,
    val franchiseId: Long?,
    val franchiseFeeRate: Double?
) {
}