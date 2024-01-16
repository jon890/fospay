package com.bifos.fospay.remittance.adapter.out.service.membership


data class Membership(
    val id: Long,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean,
) {
}