package com.bifos.fospay.money.application.port.out

interface CreateMemberMoneyPort {

    fun createMemberMoney(membershipId: Long, aggregateIdentifier: String)
}