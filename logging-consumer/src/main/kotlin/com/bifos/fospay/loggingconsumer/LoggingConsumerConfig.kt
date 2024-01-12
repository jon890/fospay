package com.bifos.fospay.loggingconsumer

import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.TopicBuilder

@Configuration
class LoggingConsumerConfig(
    @Value("\${logging.topic}")
    val topic: String
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun topic(): NewTopic {
        return TopicBuilder.name(topic)
            .partitions(1)
            .replicas(1)
            .build()
    }

    @KafkaListener(id = "my-group", topics = ["\${logging.topic}"])
    fun listen(message: String) {
        logger.info("Received Message : $message")
    }
}