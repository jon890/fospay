package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.membership.domain.Membership
import common.UseCase

@UseCase
interface ModifyMembershipUseCase {

    fun modifyMembership(command: ModifyMembershipCommand): Membership
}
