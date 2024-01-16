package com.bifos.fospay.remittance.domain

/**
 * 송금 요청에 대한 상태 클래스
 */
class RemittanceRequest private constructor(
    val requestId: String,
    val fromMembershipId: Long,
    val toBankName: String,
    val toBankAccountNumber: String,
    val remittanceType: Int,
    val amount: Int,
    val status: String
) {
    companion object {
        fun generate(
            requestId: String,
            fromMembershipId: Long,
            toBankName: String,
            toBankAccountNumber: String,
            remittanceType: Int,
            amount: Int,
            status: String
        ): RemittanceRequest {
            return RemittanceRequest(
                requestId,
                fromMembershipId,
                toBankName,
                toBankAccountNumber,
                remittanceType,
                amount,
                status
            )
        }
    }
}