package com.bifos.fospay.membership.adapter.out.persistence

import com.bifos.fospay.membership.application.port.out.FindMembershipPort
import com.bifos.fospay.membership.application.port.out.RegisterMembershipPort
import com.bifos.fospay.membership.domain.Membership
import common.PersistenceAdapter
import org.springframework.data.repository.findByIdOrNull

@PersistenceAdapter
class MembershipPersistenceAdapter(
    private val membershipRepository: SpringDataMembershipRepository
) : RegisterMembershipPort, FindMembershipPort {

    override fun createMembership(
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp
    ): MembershipJpaEntity {
        return membershipRepository.save(
            MembershipJpaEntity(
                name = membershipName.name,
                email = membershipEmail.email,
                address = membershipAddress.address,
                isValid = membershipIsValid.isValid,
                isCorp = membershipIsCorp.isCorp
            )
        )
    }

    override fun findMembershipById(id: Long): MembershipJpaEntity? {
        return membershipRepository.findByIdOrNull(id)
    }
}