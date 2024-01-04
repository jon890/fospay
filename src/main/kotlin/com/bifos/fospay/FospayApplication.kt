package com.bifos.fospay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FospayApplication

fun main(args: Array<String>) {
    runApplication<FospayApplication>(*args)
}
