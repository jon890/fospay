package com.bifos.fospay.money

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MoneyApplication

fun main(args: Array<String>) {
    runApplication<MoneyApplication>(*args)
}
