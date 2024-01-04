package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.membership.domain.Membership
import common.UseCase

@UseCase
interface FindMembershipUseCase {

    fun findMembership(command: FindMembershipCommand): Membership?
}
