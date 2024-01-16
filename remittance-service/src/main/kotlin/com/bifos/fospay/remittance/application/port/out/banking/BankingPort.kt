package com.bifos.fospay.remittance.application.port.out.banking

import com.bifos.fospay.remittance.application.port.out.money.MoneyInfo

interface BankingPort {

    fun getMembershipBankingInfo(bankName: String, bankAccountNumber: String) : BankingInfo

    fun requestFirmBanking(bankName: String, bankAccountNumber: String, amount: Int) : Boolean
}