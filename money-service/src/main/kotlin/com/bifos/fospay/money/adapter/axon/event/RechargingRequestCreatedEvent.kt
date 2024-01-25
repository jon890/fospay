package com.bifos.fospay.money.adapter.axon.event

/**
 * "충전" 동작이 요청이 생성되었다는 "이벤트"
 */
data class RechargingRequestCreatedEvent(
    val registeredBankAccountAggregateIdentifier: String,

    val bankName: String,

    val bankAccountNumber: String,

    val requestId: String,

    val membershipId: Long,

    val amount: Int
) {
}