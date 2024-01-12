package com.bifos.fospay.taskconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TaskResultProducer(
    @Value("\${task.result.topic}")
    private val topic: String,
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun topic(): NewTopic {
        return TopicBuilder.name(topic)
            .partitions(1)
            .replicas(1)
            .build()
    }

    fun sendTaskResult(key: String, task: Any) {
        val jsonStringToProduce = objectMapper.writeValueAsString(task)
        kafkaTemplate.send(key, topic, jsonStringToProduce)
    }
}