package com.bifos.fospay.banking

import com.bifos.fospay.banking.adapter.`in`.web.RegisterBankAccountRequest
import com.bifos.fospay.banking.domain.RegisteredBankAccount
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
class RegisterBankAccountControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    @DisplayName("은행 계좌 정보가 등록된다")
    fun testRegisterMembership() {
        val request = RegisterBankAccountRequest(1, "toss", "1234", true)

        val registeredBankAccount = RegisteredBankAccount.generate(
            RegisteredBankAccount.RegisteredBankAccountId(1),
            RegisteredBankAccount.MembershipId(request.membershipId),
            RegisteredBankAccount.BankName(request.bankName),
            RegisteredBankAccount.BankAccountNumber(request.bankAccountNumber),
            RegisteredBankAccount.LinkedStatusIsValid(request.linkedStatusIsValid)
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/banking/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(registeredBankAccount)))
    }
}