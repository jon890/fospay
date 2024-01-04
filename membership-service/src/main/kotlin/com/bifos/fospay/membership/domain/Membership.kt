package com.bifos.fospay.membership.domain

// 오염 되면 안되는 클래스, 고객 정보, 핵심 도메인
class Membership private constructor(
    val id: Long,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean,
) {
    companion object {
        fun generate(
            membershipId: MembershipId,
            membershipName: MembershipName,
            membershipEmail: MembershipEmail,
            membershipAddress: MembershipAddress,
            membershipIsValid: MembershipIsValid,
            membershipIsCorp: MembershipIsCorp
        ): Membership {
            return Membership(
                membershipId.id,
                membershipName.name,
                membershipEmail.email,
                membershipAddress.address,
                membershipIsValid.isValid,
                membershipIsCorp.isCorp
            )
        }
    }

    class MembershipId(
        val id: Long
    )

    class MembershipName(
        val name: String
    )

    class MembershipEmail(
        val email: String
    )

    class MembershipAddress(
        val address: String
    )

    class MembershipIsValid(
        val isValid: Boolean
    )

    class MembershipIsCorp(
        val isCorp: Boolean
    )
}