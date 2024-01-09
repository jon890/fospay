package com.bifos.fospay.banking.domain

// 오염 되면 안되는 클래스, 고객 정보, 핵심 도메인
class FirmBankingRequest private constructor(
    val id: String,
    val fromBankName: String,
    val fromBankAccount: String,
    val toBankName: String,
    val toBankAccount: String,
    val moneyAmount: Int,
    val firmBankingStatus: String // 0: 요청, 1: 완료, 2: 실패
) {
    companion object {
        fun generate(
            id: String,
            fromBankName: String,
            fromBankAccount: String,
            toBankName: String,
            toBankAccount: String,
            moneyAmount: Int,
            firmBankingStatus: String = "0"
        ): FirmBankingRequest {
            return FirmBankingRequest(
                id,
                fromBankName,
                fromBankAccount,
                toBankName,
                toBankAccount,
                moneyAmount,
                firmBankingStatus
            )
        }
    }
}