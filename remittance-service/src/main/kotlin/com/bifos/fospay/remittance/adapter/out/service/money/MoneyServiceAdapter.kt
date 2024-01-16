package com.bifos.fospay.remittance.adapter.out.service.money

import com.bifos.fospay.common.CommonHttpClient
import com.bifos.fospay.remittance.application.port.out.money.MoneyInfo
import com.bifos.fospay.remittance.application.port.out.money.MoneyPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MoneyServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.money.url}")
    private val moneyServiceUrl: String,

    private val objectMapper: ObjectMapper
) : MoneyPort {
    override fun getMoneyInfo(membershipId: Long): MoneyInfo {
        TODO("Not yet implemented")
    }

    override fun requestMoneyRecharging(membershipId: Long, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun requestMoneyIncrease(membershipId: Long, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun requestMoneyDecrease(membershipId: Long, amount: Int): Boolean {
        TODO("Not yet implemented")
    }
}