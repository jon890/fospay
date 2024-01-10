package com.bifos.fospay.banking.domain

import java.util.*

class FirmBankingRequest private constructor(
    val id: Long,
    val fromBankName: String,
    val fromBankAccount: String,
    val toBankName: String,
    val toBankAccount: String,
    val moneyAmount: Int,
    val firmBankingStatus: String, // 0: 요청, 1: 완료, 2: 실패
    val uuid: UUID
) {
    companion object {
        fun generate(
            id: Long,
            fromBankName: String,
            fromBankAccount: String,
            toBankName: String,
            toBankAccount: String,
            moneyAmount: Int,
            firmBankingStatus: String = "0",
            uuid: UUID
        ): FirmBankingRequest {
            return FirmBankingRequest(
                id,
                fromBankName,
                fromBankAccount,
                toBankName,
                toBankAccount,
                moneyAmount,
                firmBankingStatus,
                uuid
            )
        }
    }
}