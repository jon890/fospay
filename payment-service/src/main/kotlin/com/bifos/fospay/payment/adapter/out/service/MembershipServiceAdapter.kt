package com.bifos.fospay.payment.adapter.out.service

import com.bifos.fospay.common.CommonHttpClient
import com.bifos.fospay.payment.application.port.out.GetMembershipPort
import com.bifos.fospay.payment.application.port.out.MembershipStatus
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MembershipServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.membership.url}")
    private val membershipServiceUrl: String,

    private val objectMapper: ObjectMapper
) : GetMembershipPort {

    override fun getMembership(membershipId: Long): MembershipStatus {
        val url = arrayOf(membershipServiceUrl, "membership", membershipId.toString()).joinToString(separator = "/")

        try {
            val jsonResponse = commonHttpClient.sendGetRequest(url).body()
            val membership = objectMapper.readValue(jsonResponse, Membership::class.java)
            return MembershipStatus(membershipId, membership.isValid)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}