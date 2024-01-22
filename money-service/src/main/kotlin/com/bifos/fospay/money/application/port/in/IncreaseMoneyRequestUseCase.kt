package com.bifos.fospay.money.application.port.`in`

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.money.domain.MoneyChangingRequest

@UseCase
interface IncreaseMoneyRequestUseCase {

    fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest

    fun increaseMoneyAsync(command: IncreaseMoneyRequestCommand) : MoneyChangingRequest?

    fun increaseMoneyByEvent(command: IncreaseMoneyRequestCommand)
}
