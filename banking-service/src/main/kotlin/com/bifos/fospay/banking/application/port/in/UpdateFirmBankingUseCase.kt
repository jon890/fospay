package com.bifos.fospay.banking.application.port.`in`

import com.bifos.fospay.banking.domain.FirmBankingRequest
import com.bifos.fospay.common.UseCase

@UseCase
interface UpdateFirmBankingUseCase {
    fun requestFirmBankingByEvent(command: UpdateFirmBankingCommand)
}
