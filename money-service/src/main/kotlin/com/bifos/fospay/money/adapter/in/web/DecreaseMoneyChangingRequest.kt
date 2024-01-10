package com.bifos.fospay.money.adapter.`in`.web

data class DecreaseMoneyChangingRequest(
    val targetMembershipId: Long,
    val amount: Int,
)
