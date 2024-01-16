package com.bifos.fospay.remittance.application.port.out.money

data class MoneyInfo(
    val membershipId: Long,
    val balance: Int
)