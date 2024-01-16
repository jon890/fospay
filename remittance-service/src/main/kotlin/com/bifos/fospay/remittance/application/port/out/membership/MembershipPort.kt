package com.bifos.fospay.remittance.application.port.out.membership

interface MembershipPort {

    fun getMembershipStatus(membershipId: Long): MembershipStatus
}