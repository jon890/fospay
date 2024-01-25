package com.bifos.fospay.money.application.port.out

interface GetRegisteredBankAccountPort {

    fun getRegisteredBankAccount(membershipId: Long) : RegisteredBankAccountAggregateIdentifier
}