package com.bifos.fospay.common

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class LoggingProducer(
    @Value("\${logging.topic}")
    val topic: String,

    @Value("\${kafka.clusters.bootstrapservers}")
    val bootstrapServers: String
) {
    private val producer: KafkaProducer<String, String>
    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        val props = Properties().also {
            it["bootstrap.servers"] = bootstrapServers
            it["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
            it["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        }

        producer = KafkaProducer(props)
    }

    fun sendMessage(key: String, value: String) {
        val record = ProducerRecord(topic, key, value)
        producer.send(record) { metadata, exception ->
            if (exception == null) {
                logger.info("Message send successfully. Offset: ${metadata.offset()}")
            } else {
                logger.error("Failed to send message", exception)
            }
        }
    }
}