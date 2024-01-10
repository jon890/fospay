package com.bifos.fospay.banking.adapter.out.external.bank

import com.bifos.fospay.banking.application.port.out.RequestBankAccountInfoPort
import com.bifos.fospay.banking.application.port.out.RequestExternalFirmBankingPort
import com.bifos.fospay.common.ExternalSystemAdapter


@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

    override fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount {
        return BankAccount(request.bankName, request.bankAccountNumber, true)
    }

    override fun requestExternalFirmBanking(request: ExternalFirmBankingRequest): FirmBankingResult {
        // 실제로 외부 은행에 http 통신을 통해서
        // 펌뱅킹 요청을 하고

        // 그 결과를
        // 외부 은행의 실제 결과를 -> fospay의 FirmBankingResult로 파싱
        return FirmBankingResult(0)
    }
}