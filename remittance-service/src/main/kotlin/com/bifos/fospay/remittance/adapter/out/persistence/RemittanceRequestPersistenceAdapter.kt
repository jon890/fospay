package com.bifos.fospay.remittance.adapter.out.persistence

import com.bifos.fospay.common.PersistenceAdapter
import com.bifos.fospay.remittance.application.port.`in`.FindRemittanceCommand
import com.bifos.fospay.remittance.application.port.`in`.RequestRemittanceCommand
import com.bifos.fospay.remittance.application.port.out.FindRemittancePort
import com.bifos.fospay.remittance.application.port.out.RequestRemittancePort

@PersistenceAdapter
class RemittanceRequestPersistenceAdapter(
    private val remittanceRequestRepository: SpringDataRemittanceRequestRepository
) : RequestRemittancePort, FindRemittancePort {


    override fun createRemittanceRequestHistory(command: RequestRemittanceCommand): RemittanceRequestJpaEntity {
        return remittanceRequestRepository.save(
            RemittanceRequestJpaEntity(
                fromMembershipId = command.fromMembershipId!!,
                toMembershipId = command.toMembershipId,
                toBankName = command.toBankName!!,
                toBankAccountNumber = command.toBankAccountNumber!!,
                remittanceType = command.remittanceType!!,
                amount = command.amount!!,
            )
        )
    }

    override fun saveRemittanceRequestHistory(entity: RemittanceRequestJpaEntity): Boolean {
        remittanceRequestRepository.save(entity)
        return true
    }

    override fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequestJpaEntity> {
        return remittanceRequestRepository.findByFromMembershipId(command.membershipId!!)
    }
}
