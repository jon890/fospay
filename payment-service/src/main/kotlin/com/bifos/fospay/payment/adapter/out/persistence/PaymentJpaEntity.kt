package com.bifos.fospay.payment.adapter.out.persistence

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "td_payment")
@EntityListeners(AuditingEntityListener::class)
class PaymentJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val membershipId: Long,

    val price: String,

    val franchiseId: Long,

    val franchiseFeeRate: Double,

    val paymentStatus: Int,

    @CreatedDate
    var createdAt: LocalDateTime? = null
) {
}