package com.bifos.fospay.taskconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoggingConsumerApplication


fun main(args: Array<String>) {
    runApplication<LoggingConsumerApplication>(*args)
}