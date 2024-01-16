package com.bifos.fospay.remittance.adapter.`in`.web

/**
 * 송금 요청을 위한 정보가 담긴 class
 */
data class RequestRemittanceRequest(
    val fromMembershipId: Long?,
    val toMembershipId: Long?,
    val toBankName: String?,
    val toBankAccountNumber: String?,
    val amount: Int?,
    val remittanceType: Int? // 0: Membership, 1: Bank
)
