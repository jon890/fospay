package com.bifos.fospay.banking.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataRequestFirmBankingRepository : JpaRepository<FirmBankingRequestJpaEntity, Long> {
}