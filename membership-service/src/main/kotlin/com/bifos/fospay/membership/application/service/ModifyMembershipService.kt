package com.bifos.fospay.membership.application.service

import com.bifos.fospay.membership.adapter.out.persistence.MembershipMapper
import com.bifos.fospay.membership.application.port.`in`.ModifyMembershipCommand
import com.bifos.fospay.membership.application.port.`in`.ModifyMembershipUseCase
import com.bifos.fospay.membership.application.port.out.ModifyMembershipPort
import com.bifos.fospay.membership.domain.Membership
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyMembershipService(
    private val modifyMembershipPort: ModifyMembershipPort,
    private val membershipMapper: MembershipMapper
) : ModifyMembershipUseCase {

    override fun modifyMembership(command: ModifyMembershipCommand): Membership {
        val membershipJpaEntity = modifyMembershipPort.modifyMembership(
            Membership.MembershipId(command.id!!),
            membershipName = command.name?.let { Membership.MembershipName(command.name) },
            membershipEmail = command.email?.let { Membership.MembershipEmail(command.email) },
            membershipAddress = command.address?.let { Membership.MembershipAddress(command.address) },
            membershipIsValid = command.isValid?.let { Membership.MembershipIsValid(command.isValid) },
            membershipIsCorp = command.isCorp?.let { Membership.MembershipIsCorp(command.isCorp) },
        )

        return membershipMapper.mapToDomainEntity(membershipJpaEntity)
    }
}
