package com.bifos.fospay.membership.application.port.out

import com.bifos.fospay.membership.adapter.out.persistence.MembershipJpaEntity
import com.bifos.fospay.membership.domain.Membership

interface RegisterMembershipPort {

    fun createMembership(
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp
    ): MembershipJpaEntity
}