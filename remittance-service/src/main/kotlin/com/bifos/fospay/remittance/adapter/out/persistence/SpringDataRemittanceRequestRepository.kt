package com.bifos.fospay.remittance.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataRemittanceRequestRepository : JpaRepository<RemittanceRequestJpaEntity, Long> {

    fun findByFromMembershipId(membershipId: Long) : List<RemittanceRequestJpaEntity>
}