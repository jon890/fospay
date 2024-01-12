package com.bifos.fospay.money.application.port.out

import com.bifos.fospay.common.RechargingMoneyTask

interface SendRechargingMoneyTaskPort {

    fun sendRechargingMoneyTaskPort(task : RechargingMoneyTask)
}