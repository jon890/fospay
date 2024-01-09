package com.bifos.fospay.membership.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "td_membership")
class MembershipJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    var email: String,

    var address: String,

    var isValid: Boolean,

    var isCorp: Boolean,
) {

    override fun toString(): String {
        return "MembershipJpaEntity(membershipId=$id, name='$name', address='$address', isValid=$isValid, isCorp=$isCorp)"
    }
}