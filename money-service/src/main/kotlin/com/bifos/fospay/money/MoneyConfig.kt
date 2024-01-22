package com.bifos.fospay.money

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.bifos.fospay.common")
class MoneyConfig(
    objectMapper: ObjectMapper
) {

    init {
        objectMapper.registerModules(KotlinModule.Builder().build())
    }
}