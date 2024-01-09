package com.bifos.fospay.membership.application.port.`in`

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.membership.domain.Membership

@UseCase
interface RegisterMembershipUseCase {

    fun registerMembership(command: RegisterMembershipCommand): Membership
}
