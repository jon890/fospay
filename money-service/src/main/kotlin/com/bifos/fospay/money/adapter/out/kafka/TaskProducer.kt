package com.bifos.fospay.money.adapter.out.kafka

import com.bifos.fospay.common.RechargingMoneyTask
import com.bifos.fospay.money.application.port.out.SendRechargingMoneyTaskPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TaskProducer(
    @Value("\${task.topic}")
    val topic: String,

    val kafkaTemplate: KafkaTemplate<String, String>,

    val objectMapper: ObjectMapper
) : SendRechargingMoneyTaskPort {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun taskTopic(): NewTopic {
        return TopicBuilder.name(topic)
            .partitions(1)
            .replicas(1)
            .build()
    }

    private fun sendMessage(key: String, task: RechargingMoneyTask) {
        val jsonStringToProduce = objectMapper.writeValueAsString(task)
        kafkaTemplate.send(topic, key, jsonStringToProduce)
    }


    override fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask) {
        sendMessage(task.taskId, task)
    }
}