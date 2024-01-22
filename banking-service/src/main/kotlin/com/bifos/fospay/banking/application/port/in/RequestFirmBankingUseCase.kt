package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.banking.domain.FirmBankingRequest
import com.bifos.fospay.common.UseCase

@UseCase
interface RequestFirmBankingUseCase {

    fun requestFirmBanking(command: RequestFirmBankingCommand): FirmBankingRequest
    fun requestFirmBankingByEvent(command: RequestFirmBankingCommand)
}
