package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.membership.domain.Membership

@UseCase
interface FindMembershipUseCase {

    fun findMembership(command: FindMembershipCommand): Membership?
}
