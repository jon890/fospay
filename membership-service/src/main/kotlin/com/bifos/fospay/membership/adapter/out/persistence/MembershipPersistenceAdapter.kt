package com.bifos.fospay.membership.adapter.out.persistence

import com.bifos.fospay.common.PersistenceAdapter
import com.bifos.fospay.membership.application.port.out.FindMembershipPort
import com.bifos.fospay.membership.application.port.out.ModifyMembershipPort
import com.bifos.fospay.membership.application.port.out.RegisterMembershipPort
import com.bifos.fospay.membership.domain.Membership
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull

@PersistenceAdapter
class MembershipPersistenceAdapter(
    private val membershipRepository: SpringDataMembershipRepository
) : RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

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

    override fun modifyMembership(
        membershipId: Membership.MembershipId,
        membershipName: Membership.MembershipName?,
        membershipEmail: Membership.MembershipEmail?,
        membershipAddress: Membership.MembershipAddress?,
        membershipIsValid: Membership.MembershipIsValid?,
        membershipIsCorp: Membership.MembershipIsCorp?
    ): MembershipJpaEntity {
        val entity = membershipRepository.findByIdOrNull(membershipId.id) ?: throw EntityNotFoundException()
        membershipName?.let { entity.name = it.name }
        membershipEmail?.let { entity.email = it.email }
        membershipAddress?.let { entity.address = it.address }
        membershipIsValid?.let { entity.isValid = it.isValid }
        membershipIsCorp?.let { entity.isCorp = it.isCorp }
        return entity
    }
}
