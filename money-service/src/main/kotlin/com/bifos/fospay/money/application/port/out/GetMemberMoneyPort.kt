package com.bifos.fospay.money.application.port.out

import com.bifos.fospay.money.adapter.out.persistence.MemberMoneyJpaEntity

interface GetMemberMoneyPort {

    fun getMemberMoney(membershipId: Long) : MemberMoneyJpaEntity
}