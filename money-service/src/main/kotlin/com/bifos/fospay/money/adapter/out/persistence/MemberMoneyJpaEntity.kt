package com.bifos.fospay.money.adapter.out.persistence

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "td_member_money")
@EntityListeners(AuditingEntityListener::class)
class MemberMoneyJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val membershipId: Long,

    var balance: Int,

    @CreatedDate
    var createdAt: LocalDateTime? = null
) {
}