package com.bifos.fospay.money.adapter.out.service

import com.bifos.fospay.common.CommonHttpClient
import com.bifos.fospay.money.application.port.out.GetRegisteredBankAccountPort
import com.bifos.fospay.money.application.port.out.RegisteredBankAccountAggregateIdentifier
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BankingServiceAdapter(
    private val commonHttpClient: CommonHttpClient,
    @Value("\${service.banking.url}")
    private val bankingServiceUrl: String,
    private val objectMapper: ObjectMapper
) : GetRegisteredBankAccountPort {

    override fun getRegisteredBankAccount(membershipId: Long): RegisteredBankAccountAggregateIdentifier {
        val url = listOf(bankingServiceUrl, "banking/account", membershipId).joinToString("/")
        try {
            val jsonResponse = commonHttpClient.sendGetRequest(url).body()
            val registeredBankAccount =
                objectMapper.readValue(jsonResponse, RegisteredBankAccount::class.java)

            return RegisteredBankAccountAggregateIdentifier(
                id = registeredBankAccount.id,
                aggregateIdentifier = registeredBankAccount.aggregateIdentifier,
                membershipId = registeredBankAccount.membershipId,
                bankName = registeredBankAccount.bankName,
                bankAccountNumber = registeredBankAccount.bankAccountNumber
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}