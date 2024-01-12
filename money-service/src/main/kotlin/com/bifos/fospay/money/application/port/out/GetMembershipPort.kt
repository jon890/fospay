package com.bifos.fospay.money.application.port.out

interface GetMembershipPort {

    fun getMembership(membershipId: Long): MembershipStatus
}