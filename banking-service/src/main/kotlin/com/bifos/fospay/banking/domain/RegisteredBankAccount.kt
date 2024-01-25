package com.bifos.fospay.banking.domain

// 오염 되면 안되는 클래스, 고객 정보, 핵심 도메인
class RegisteredBankAccount private constructor(
    val id: Long,
    val membershipId: Long,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean,
    val aggregateIdentifier: String
) {
    companion object {
        fun generate(
            id: Long,
            membershipId: Long,
            bankName: String,
            bankAccountNumber: String,
            linkedStatusIsValid: Boolean,
            aggregateIdentifier: String
        ): RegisteredBankAccount {
            return RegisteredBankAccount(
                id,
                membershipId,
                bankName,
                bankAccountNumber,
                linkedStatusIsValid,
                aggregateIdentifier
            )
        }
    }
}