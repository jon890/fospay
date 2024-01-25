package com.bifos.fospay.banking.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "td_registered_bank_account")
class RegisteredBankAccountJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val membershipId: Long,

    val bankName: String,

    val bankAccountNumber: String,

    var linkedStatusIsValid: Boolean,

    val aggregateIdentifier: String
) {
}