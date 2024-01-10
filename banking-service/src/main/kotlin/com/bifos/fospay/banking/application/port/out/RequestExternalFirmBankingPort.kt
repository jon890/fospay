package com.bifos.fospay.banking.application.port.out

import com.bifos.fospay.banking.adapter.out.external.bank.ExternalFirmBankingRequest
import com.bifos.fospay.banking.adapter.out.external.bank.FirmBankingResult

interface RequestExternalFirmBankingPort {

    fun requestExternalFirmBanking(request: ExternalFirmBankingRequest) : FirmBankingResult
}