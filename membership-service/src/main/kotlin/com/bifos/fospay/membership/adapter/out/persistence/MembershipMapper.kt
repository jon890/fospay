package com.bifos.fospay.membership.adapter.out.persistence

import com.bifos.fospay.membership.domain.Membership
import org.springframework.stereotype.Component

@Component
class MembershipMapper {

    fun mapToDomainEntity(membershipJpaEntity: MembershipJpaEntity): Membership {
        return Membership.generate(
            Membership.MembershipId(membershipJpaEntity.id!!),
            Membership.MembershipName(membershipJpaEntity.name),
            Membership.MembershipEmail(membershipJpaEntity.email),
            Membership.MembershipAddress(membershipJpaEntity.address),
            Membership.MembershipIsValid(membershipJpaEntity.isValid),
            Membership.MembershipIsCorp(membershipJpaEntity.isCorp),
        )
    }
}