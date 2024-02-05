package com.bifos.fospay.payment.adapter.`in`.web

import com.bifos.fospay.common.WebAdapter
import com.bifos.fospay.payment.application.port.`in`.RequestPaymentCommand
import com.bifos.fospay.payment.application.port.`in`.RequestPaymentUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestPaymentController(
    private val requestPaymentUseCase: RequestPaymentUseCase
) {

    @PostMapping("/payment")
    fun requestPayment(request: PaymentRequest) {
        requestPaymentUseCase.requestPayment(
            RequestPaymentCommand(
                request.membershipId,
                request.price,
                request.franchiseId,
                request.franchiseFeeRate,
            )
        )
    }
}