package com.bifos.fospay.money.adapter.out.persistence

import com.bifos.fospay.common.PersistenceAdapter
import com.bifos.fospay.money.application.port.out.IncreaseMoneyPort
import java.util.*

@PersistenceAdapter
class MoneyChangingRequestPersistenceAdapter(
    private val moneyChangingRequestRepository: SpringDataMoneyChangingRequestRepository,
    private val memberMoneyRepository: SpringDataMemberMoneyRepository
) : IncreaseMoneyPort {

    override fun createMoneyChangingRequest(
        targetMembershipId: Long,
        moneyChangingType: Int,
        changingMoneyAmount: Int,
        moneyChangingStatus: Int,
        uuid: UUID
    ): MoneyChangingRequestJpaEntity {
        return moneyChangingRequestRepository.save(
            MoneyChangingRequestJpaEntity(
                targetMembershipId = targetMembershipId,
                moneyChangingType = moneyChangingType,
                changingMoneyAmount = changingMoneyAmount,
                moneyChangingStatus = moneyChangingStatus,
                uuid = UUID.randomUUID()
            )
        )
    }

    override fun increaseMoney(membershipId: Long, balance: Int): MemberMoneyJpaEntity {
        var entity = memberMoneyRepository.findByMembershipId(membershipId)

        return if (entity == null) {
            entity = MemberMoneyJpaEntity(membershipId = membershipId, balance = balance)
            memberMoneyRepository.save(entity)
        } else {
            entity.balance += balance
            entity
        }
    }
}
