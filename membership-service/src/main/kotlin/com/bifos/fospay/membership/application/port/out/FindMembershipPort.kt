package com.bifos.fospay.membership.application.port.out

import com.bifos.fospay.membership.adapter.out.persistence.MembershipJpaEntity

interface FindMembershipPort {
    fun findMembershipById(
        id: Long
    ): MembershipJpaEntity?
}