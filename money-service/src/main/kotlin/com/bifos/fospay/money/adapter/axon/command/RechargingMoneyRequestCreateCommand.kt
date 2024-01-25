package com.bifos.fospay.money.adapter.axon.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * 머니 충전 사가를 시작하는 커맨드
 */
data class RechargingMoneyRequestCreateCommand(
    @TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val rechargingRequestId: String,

    val membershipId: Long,

    val amount: Int
) {
}