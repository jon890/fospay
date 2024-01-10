package com.bifos.fospay.money.domain

class MemberMoney private constructor(
    val id: Long,
    val membershipId: Long,
    val balance: Int,
) {
    companion object {
        fun generate(
            id: Long,
            membershipId: Long,
            balance: Int
        ): MemberMoney {
            return MemberMoney(
                id, membershipId, balance
            )
        }
    }
}