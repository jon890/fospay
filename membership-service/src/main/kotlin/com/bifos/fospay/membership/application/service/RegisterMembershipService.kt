package com.bifos.fospay.membership.application.service

import com.bifos.fospay.membership.adapter.out.persistence.MembershipMapper
import com.bifos.fospay.membership.application.port.`in`.RegisterMembershipCommand
import com.bifos.fospay.membership.application.port.`in`.RegisterMembershipUseCase
import com.bifos.fospay.membership.application.port.out.RegisterMembershipPort
import com.bifos.fospay.membership.domain.Membership
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterMembershipService(
    private val registerMembershipPort: RegisterMembershipPort,
    private val membershipMapper: MembershipMapper
) : RegisterMembershipUseCase {
    override fun registerMembership(command: RegisterMembershipCommand): Membership {
        val membershipJpaEntity = registerMembershipPort.createMembership(
            Membership.MembershipName(command.name!!),
            Membership.MembershipEmail(command.email!!),
            Membership.MembershipAddress(command.address),
            Membership.MembershipIsValid(command.isValid),
            Membership.MembershipIsCorp(command.isCorp!!),
        )

        return membershipMapper.mapToDomainEntity(membershipJpaEntity)
    }
}
