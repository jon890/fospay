package com.bifos.fospay.banking.adapter.out.external.bank

import com.bifos.fospay.banking.application.port.out.RequestBankAccountInfoPort
import com.bifos.fospay.common.ExternalSystemAdapter


@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort {

    override fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount {
        return BankAccount(request.bankName, request.bankAccountNumber, true)
    }
}