package com.chargebee.ingestion.service;

import com.chargebee.ingestion.models.UsageRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsageRecordProducer {

    private static final Logger logger = LoggerFactory.getLogger(UsageRecordProducer.class);
    private final KafkaTemplate<String, UsageRecord> kafkaTemplate;
    private final String topic;

    @Autowired
    public UsageRecordProducer(KafkaTemplate<String, UsageRecord> kafkaTemplate,
                               @Value("${kafka.topic.usage-records}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendUsageRecord(UsageRecord usageRecord) {
        try {
            logger.info("Kafka topic " + topic);
            kafkaTemplate.send(topic, usageRecord);
        } catch (Exception exception) {
            logger.info("Kafka ingestion error " + exception.getMessage());
        }

    }
}