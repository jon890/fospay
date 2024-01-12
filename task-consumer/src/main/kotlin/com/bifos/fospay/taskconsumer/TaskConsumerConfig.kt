package com.bifos.fospay.taskconsumer

import com.bifos.fospay.common.RechargingMoneyTask
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.TopicBuilder

@Configuration
class TaskConsumerConfig(
    @Value("\${task.topic}")
    val taskTopic: String,

    private val taskResultProducer: TaskResultProducer,

    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun taskTopic(): NewTopic {
        return TopicBuilder.name(taskTopic)
            .partitions(1)
            .replicas(1)
            .build()
    }

    @KafkaListener(id = "my-group", topics = ["\${task.topic}"])
    fun listen(message: String) {
        logger.info("Received Message : $message")
        // message : RechargingMoneyTask (json String)
        val task = objectMapper.readValue(message, RechargingMoneyTask::class.java)

        // task run
        for (subTask in task.subTasks) {
            // what subtask, membership, banking
            // external port, adapter

            // all subtask is done. true
            subTask.status = "success"
        }

        // produce taskResult
        taskResultProducer.sendTaskResult(task.taskId, task)
    }
}