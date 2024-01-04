package com.bifos.fospay.membership.application.service

import com.bifos.fospay.membership.adapter.out.persistence.MembershipMapper
import com.bifos.fospay.membership.application.port.`in`.FindMembershipCommand
import com.bifos.fospay.membership.application.port.`in`.FindMembershipUseCase
import com.bifos.fospay.membership.application.port.out.FindMembershipPort
import com.bifos.fospay.membership.domain.Membership
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FindMembershipService(
    private val findMembershipPort: FindMembershipPort,
    private val membershipMapper: MembershipMapper
) : FindMembershipUseCase {

    override fun findMembership(command: FindMembershipCommand): Membership? {
        val membershipJpaEntity = findMembershipPort.findMembershipById(command.id!!)
        return membershipJpaEntity?.let { membershipMapper.mapToDomainEntity(it) }
    }
}
