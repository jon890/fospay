package com.bifos.fospay.payment.application.port.out

interface GetMembershipPort {

    fun getMembership(membershipId: Long): MembershipStatus
}