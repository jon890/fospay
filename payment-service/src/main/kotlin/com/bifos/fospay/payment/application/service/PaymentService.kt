package com.bifos.fospay.payment.application.service

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.payment.application.port.`in`.RequestPaymentCommand
import com.bifos.fospay.payment.application.port.`in`.RequestPaymentUseCase
import com.bifos.fospay.payment.application.port.out.CreatePaymentPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@UseCase
@Service
@Transactional
class PaymentService(
    private val createPaymentPort: CreatePaymentPort
) : RequestPaymentUseCase {

    override fun requestPayment(command: RequestPaymentCommand) {

        // 멤버십, 뱅킹, 머니, 충전

        createPaymentPort.createPayment(
            command.membershipId!!,
            command.price!!,
            command.franchiseId!!,
            command.franchiseFeeRate!!
        )
    }
}