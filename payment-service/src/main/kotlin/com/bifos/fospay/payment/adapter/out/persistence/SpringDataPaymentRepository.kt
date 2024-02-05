package com.bifos.fospay.payment.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataPaymentRepository : JpaRepository<PaymentJpaEntity, Long> {

//    fun findByMembershipId(membershipId: Long): PaymentJpaEntity?
}