package com.bifos.fospay.remittance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class RemittanceApplication

fun main(args: Array<String>) {
    runApplication<RemittanceApplication>(*args)
}