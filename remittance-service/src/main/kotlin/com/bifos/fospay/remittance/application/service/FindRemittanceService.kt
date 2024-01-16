package com.bifos.fospay.remittance.application.service

import com.bifos.fospay.common.UseCase
import com.bifos.fospay.remittance.adapter.out.persistence.RemittanceRequestMapper
import com.bifos.fospay.remittance.application.port.`in`.FindRemittanceCommand
import com.bifos.fospay.remittance.application.port.`in`.FindRemittanceUseCase
import com.bifos.fospay.remittance.application.port.out.FindRemittancePort
import com.bifos.fospay.remittance.domain.RemittanceRequest
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class FindRemittanceService(
    private val findRemittancePort: FindRemittancePort,
    private val remittanceRequestMapper: RemittanceRequestMapper
) : FindRemittanceUseCase {

    override fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequest> {
        // assert
        command.membershipId!!

        return findRemittancePort.findRemittanceHistory(command).map { remittanceRequestMapper.mapToDomainEntity(it) }
    }
}