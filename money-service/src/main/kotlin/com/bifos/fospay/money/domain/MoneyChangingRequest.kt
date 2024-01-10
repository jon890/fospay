package com.bifos.fospay.money.domain

import java.time.LocalDateTime
import java.util.*

// 오염 되면 안되는 클래스, 고객 정보, 핵심 도메인
class MoneyChangingRequest private constructor(
    val id: Long,
    val targetMembershipId: Long,
    val changingType: Int, // enum. 0: 증액, 1: 감액
    val changingMoneyAmount: Int,
    val changingMoneyStatus: Int,
    val uuid: UUID,
    val createdAt: LocalDateTime
) {
    companion object {
        fun generate(
            id: Long,
            targetMembershipId: Long,
            changingType: Int,
            changingMoneyAmount: Int,
            changingMoneyStatus: Int,
            uuid: UUID,
            createdAt: LocalDateTime
        ): MoneyChangingRequest {
            return MoneyChangingRequest(
                id, targetMembershipId, changingType, changingMoneyAmount, changingMoneyStatus, uuid, createdAt
            )
        }
    }
}