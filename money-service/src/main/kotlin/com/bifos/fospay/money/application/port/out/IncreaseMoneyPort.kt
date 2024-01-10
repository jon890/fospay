package com.bifos.fospay.money.application.port.out

import com.bifos.fospay.money.adapter.out.persistence.MemberMoneyJpaEntity
import com.bifos.fospay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity
import java.util.*

interface IncreaseMoneyPort {

    fun createMoneyChangingRequest(
        targetMembershipId: Long,
        moneyChangingType: Int,
        changingMoneyAmount: Int,
        moneyChangingStatus: Int,
        uuid: UUID
    ): MoneyChangingRequestJpaEntity

    fun increaseMoney(
        membershipId: Long,
        balance: Int
    ): MemberMoneyJpaEntity
}