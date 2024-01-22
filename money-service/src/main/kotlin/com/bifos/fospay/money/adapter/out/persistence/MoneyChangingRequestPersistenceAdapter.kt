package com.bifos.fospay.money.adapter.out.persistence

import com.bifos.fospay.common.PersistenceAdapter
import com.bifos.fospay.money.application.port.out.CreateMemberMoneyPort
import com.bifos.fospay.money.application.port.out.GetMemberMoneyPort
import com.bifos.fospay.money.application.port.out.IncreaseMoneyPort
import org.springframework.transaction.annotation.Transactional
import java.util.*

@PersistenceAdapter
class MoneyChangingRequestPersistenceAdapter(
    private val moneyChangingRequestRepository: SpringDataMoneyChangingRequestRepository,
    private val memberMoneyRepository: SpringDataMemberMoneyRepository
) : IncreaseMoneyPort, CreateMemberMoneyPort, GetMemberMoneyPort {

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

    @Transactional
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

    override fun createMemberMoney(membershipId: Long, aggregateIdentifier: String) {
        val entity = memberMoneyRepository.save(
            MemberMoneyJpaEntity(
                membershipId = membershipId,
                balance = 0,
                aggregateIdentifier = aggregateIdentifier
            )
        )
    }

    override fun getMemberMoney(membershipId: Long): MemberMoneyJpaEntity {
        val find = memberMoneyRepository.findByMembershipId(membershipId)
        return find ?: memberMoneyRepository.save(MemberMoneyJpaEntity(membershipId = membershipId, balance = 0))
    }
}
