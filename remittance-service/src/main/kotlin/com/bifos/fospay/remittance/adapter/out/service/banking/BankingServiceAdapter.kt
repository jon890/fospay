package com.bifos.fospay.remittance.adapter.out.service.banking

import com.bifos.fospay.common.CommonHttpClient
import com.bifos.fospay.remittance.application.port.out.banking.BankingInfo
import com.bifos.fospay.remittance.application.port.out.banking.BankingPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BankingServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.banking.url}")
    private val bankingServiceUrl: String,

    private val objectMapper: ObjectMapper
) : BankingPort {


    override fun getMembershipBankingInfo(bankName: String, bankAccountNumber: String): BankingInfo {
        TODO("Not yet implemented")
    }

    override fun requestFirmBanking(bankName: String, bankAccountNumber: String, amount: Int): Boolean {
        TODO("Not yet implemented")
    }
}