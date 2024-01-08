package com.bifos.fospay.membership

import com.bifos.fospay.membership.adapter.`in`.web.RegisterMembershipRequest
import com.bifos.fospay.membership.domain.Membership
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class RegisterMembershipControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    @DisplayName("유저가 가입된다")
    fun testRegisterMembership() {
        val request = RegisterMembershipRequest("name", "address", "email", true)

        val memberShip = Membership.generate(
            Membership.MembershipId(1),
            Membership.MembershipName(request.name),
            Membership.MembershipEmail(request.email),
            Membership.MembershipAddress(request.address),
            Membership.MembershipIsValid(true),
            Membership.MembershipIsCorp(request.isCorp),
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(memberShip)))
    }
}