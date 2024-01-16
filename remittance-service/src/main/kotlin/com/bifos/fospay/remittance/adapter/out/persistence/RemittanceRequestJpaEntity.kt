package com.bifos.fospay.remittance.adapter.out.persistence

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "td_remittance_request")
@EntityListeners(AuditingEntityListener::class)
class RemittanceRequestJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val fromMembershipId: Long,

    val toMembershipId: Long?,

    val toBankName: String,

    val toBankAccountNumber: String,

    val remittanceType: Int, // 0: membership (내부 고객), 1: bank (외부 은행 계좌) TODO enum

    val amount: Int,

    var remittanceStatus: String = "start",

    @CreatedDate
    var createdAt: LocalDateTime? = null
) {
}