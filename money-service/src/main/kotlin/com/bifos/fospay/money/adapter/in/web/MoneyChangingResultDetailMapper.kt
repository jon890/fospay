package com.bifos.fospay.money.adapter.`in`.web

import com.bifos.fospay.money.domain.MoneyChangingRequest
import org.springframework.stereotype.Component

@Component
class MoneyChangingResultDetailMapper {

    fun mapToMoneyChangingResultDetail(moneyChangingRequest: MoneyChangingRequest): MoneyChangingResultDetail {
        return MoneyChangingResultDetail(
            moneyChangingRequest.id,
            moneyChangingRequest.changingType,
            moneyChangingRequest.changingMoneyAmount,
            moneyChangingRequest.changingMoneyStatus
        )
    }
}