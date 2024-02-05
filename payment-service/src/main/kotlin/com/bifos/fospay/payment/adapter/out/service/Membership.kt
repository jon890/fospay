package com.bifos.fospay.payment.adapter.out.service


data class Membership(
    val id: Long,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean,
) {
}