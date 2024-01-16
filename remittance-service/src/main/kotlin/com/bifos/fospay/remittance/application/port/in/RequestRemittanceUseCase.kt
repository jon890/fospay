package com.bifos.fospay.remittance.application.port.`in`

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.remittance.domain.RemittanceRequest

@UseCase
interface RequestRemittanceUseCase {

    fun requestRemittance(command: RequestRemittanceCommand): RemittanceRequest?
}
