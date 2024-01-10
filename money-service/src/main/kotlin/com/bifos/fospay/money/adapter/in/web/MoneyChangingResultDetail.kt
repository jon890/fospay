package com.bifos.fospay.money.adapter.`in`.web

data class MoneyChangingResultDetail(
    val requestId: Long,
    val moneyChangingType: Int,
    val amount: Int,
    val resultStatus: Int
)
