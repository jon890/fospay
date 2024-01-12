package com.bifos.fospay.banking.application.port.out

interface GetMembershipPort {

    fun getMembership(membershipId: Long): MembershipStatus
}