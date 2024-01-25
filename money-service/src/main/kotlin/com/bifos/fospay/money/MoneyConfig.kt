package com.bifos.fospay.money

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.security.AnyTypePermission
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.bifos.fospay.common")
class MoneyConfig(
    objectMapper: ObjectMapper,
    xStream: XStream
) {
    init {
        objectMapper.registerModules(KotlinModule.Builder().build())
        xStream.addPermission(AnyTypePermission.ANY)
    }
}