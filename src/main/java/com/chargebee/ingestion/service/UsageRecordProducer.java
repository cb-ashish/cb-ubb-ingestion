package com.chargebee.ingestion.service;

import com.chargebee.ingestion.models.UsageRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
        if (usageRecord == null) {
            logger.error("UsageRecord is null, not sending to Kafka.");
            return;
        }

        logger.info("Sending UsageRecord to Kafka topic: {}", topic);

        ListenableFuture<SendResult<String, UsageRecord>> future = kafkaTemplate.send(topic, usageRecord);

        future.addCallback(new ListenableFutureCallback<SendResult<String, UsageRecord>>() {
            @Override
            public void onSuccess(SendResult<String, UsageRecord> result) {
                logger.info("Successfully sent UsageRecord=[{}] with offset=[{}]", usageRecord, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Failed to send UsageRecord=[{}] due to : {}", usageRecord, ex.getMessage());
            }
        });
    }
}
