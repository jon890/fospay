package com.bifos.fospay.money.adapter.out.persistence

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "td_money_changing_request")
@EntityListeners(AuditingEntityListener::class)
class MoneyChangingRequestJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val targetMembershipId: Long,

    val moneyChangingType: Int,

    val changingMoneyAmount: Int,

    val moneyChangingStatus: Int,

    val uuid: UUID,

    @CreatedDate
    var createdAt: LocalDateTime? = null
) {
}