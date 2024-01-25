package com.bifos.fospay.banking.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataRegisteredBankAccountRepository : JpaRepository<RegisteredBankAccountJpaEntity, Long> {

    fun findByMembershipId(membershipId: Long): RegisteredBankAccountJpaEntity
}