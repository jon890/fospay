package com.bifos.fospay.banking.domain

// 오염 되면 안되는 클래스, 고객 정보, 핵심 도메인
class RegisteredBankAccount private constructor(
    val id: Long,
    val membershipId: Long,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean
) {
    companion object {
        fun generate(
            registeredBankAccountId: RegisteredBankAccountId,
            membershipId: MembershipId,
            bankName: BankName,
            bankAccountNumber: BankAccountNumber,
            linkedStatusIsValid: LinkedStatusIsValid
        ): RegisteredBankAccount {
            return RegisteredBankAccount(
                registeredBankAccountId.id,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid
            )
        }
    }

    class RegisteredBankAccountId(
        val id: Long
    )

    class MembershipId(
        val membershipId: Long
    )

    class BankName(
        val bankName: String
    )

    class BankAccountNumber(
        val bankAccountNumber: String
    )

    class LinkedStatusIsValid(
        val linkedStatusIsValid: Boolean
    )
}