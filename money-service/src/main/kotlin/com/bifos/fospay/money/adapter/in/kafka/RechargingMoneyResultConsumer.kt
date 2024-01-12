package com.bifos.fospay.money.adapter.`in`.kafka

import com.bifos.fospay.common.CountDownLatchManager
import com.bifos.fospay.common.LoggingProducer
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
class RechargingMoneyResultConsumer(
    @Value("\${task.result.topic}")
    val topic: String,

    private val countDownLatchManager: CountDownLatchManager,
    private val objectMapper: ObjectMapper,
    private val loggingProducer: LoggingProducer
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun taskResultTopic(): NewTopic {
        return TopicBuilder.name(topic)
            .partitions(1)
            .replicas(1)
            .build()
    }

    @KafkaListener(id = "my-group", topics = ["\${task.result.topic}"])
    fun listen(message: String) {
        logger.info("Received Message : $message")

        // record: RechargingMoneyTask, (all subTask is done)
        val task = objectMapper.readValue(message, RechargingMoneyTask::class.java)

        var taskResult = true
        for (subTask in task.subTasks) {
            if (subTask.status == "fail") {
                taskResult = false
                break
            }
        }

        if (taskResult) {
            loggingProducer.sendMessage(task.taskId, "task success")
            countDownLatchManager.setDataForKey(task.taskId, "success")
        } else {
            loggingProducer.sendMessage(task.taskId, "task failed")
            countDownLatchManager.setDataForKey(task.taskId, "failed")
        }

        Thread.sleep(3000)

        countDownLatchManager.getCountDownLatch(task.taskId)?.countDown()
    }
}