package com.bifos.fospay.payment.application.port.`in`

interface RequestPaymentUseCase {

    fun requestPayment(command : RequestPaymentCommand)
}
