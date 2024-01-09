package com.bifos.fospay.banking.application.port.out

import com.bifos.fospay.banking.adapter.out.external.bank.BankAccount
import com.bifos.fospay.banking.adapter.out.external.bank.GetBankAccountRequest

interface RequestBankAccountInfoPort {

    fun getBankAccountInfo(request : GetBankAccountRequest) : BankAccount
}