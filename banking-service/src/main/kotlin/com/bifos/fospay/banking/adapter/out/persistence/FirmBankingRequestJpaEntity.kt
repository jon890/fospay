package com.bifos.fospay.banking.adapter.out.persistence

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "td_request_firmbanking")
class FirmBankingRequestJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val fromBankName: String,

    val fromBankAccountNumber: String,

    val toBankName: String,

    val toBankAccountNumber: String,

    val moneyAmount: Int,

    var firmBankingStatus: String,

    val uuid: UUID,

    val aggregateIdentifier: String? = null
) {
}