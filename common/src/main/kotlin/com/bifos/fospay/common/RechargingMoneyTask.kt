package com.bifos.fospay.common

/**
 * fospay 머니 충전 태스크
 */
data class RechargingMoneyTask(
    val taskId: String,
    val taskName: String,
    val membershipId: Long,
    val subTasks: List<SubTask>,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int
) {
}