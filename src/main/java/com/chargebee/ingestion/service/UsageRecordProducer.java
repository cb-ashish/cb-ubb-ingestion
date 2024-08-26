package com.chargebee.ingestion.service;

import com.chargebee.ingestion.models.UsageRecord;
import com.squareup.wire.schema.SchemaLoader;
import kotlinx.serialization.SerializationException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

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
    public Schema loadSchema(String schemaFileName) throws IOException {
        // Access the schema file from the classpath
        ClassPathResource resource = new ClassPathResource(schemaFileName);
        // Read the schema file content
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            // Read the schema content as a string
            String schemaJson = reader.lines().reduce("", (acc, line) -> acc + line);
            // Parse the schema
            return new Schema.Parser().parse(schemaJson);
        }
    }


    public void sendUsageRecord(UsageRecord usageRecord) throws IOException {
        if (usageRecord == null) {
            logger.error("UsageRecord is null, not sending to Kafka.");
            return;
        }

        logger.info("Sending UsageRecord to Kafka topic: {}", topic);
        Schema schemaUsage = null;
        try {
            schemaUsage = loadSchema("UsageRecord.avsc");
            logger.info("Schema loaded successfully:\n" + schemaUsage.toString(true));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to load schema");
        }


        GenericRecord genericUsageRecord = new GenericData.Record(schemaUsage);
        try (KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(kafkaTemplate.getProducerFactory().getConfigurationProperties())) {
            logger.info("Producer Connection established with {}", kafkaTemplate.getProducerFactory().getConfigurationProperties());
            String messageKey = UUID.randomUUID().toString();
            final ProducerRecord<String, GenericRecord> record = new ProducerRecord<>(topic, messageKey, genericUsageRecord);
            genericUsageRecord.put("id", usageRecord.getId());
            genericUsageRecord.put("usage_timestamp", usageRecord.getUsageTimestamp());
            genericUsageRecord.put("attributes", usageRecord.getAttributes());
            genericUsageRecord.put("subscription_id", usageRecord.getSubscriptionId());
            genericUsageRecord.put("site_id", usageRecord.getSiteId());
            logger.info("Producer Generic record {}", record);
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    logger.error("Failed to send UsageRecord=[{}] due to : {}", usageRecord, exception.getMessage());
                } else {
                    logger.info("Successfully sent UsageRecord=[{}] to topic=[{}] partition=[{}] offset=[{}]",
                            usageRecord, metadata.topic(), metadata.partition(), metadata.offset());
                }
            });
        } catch (final SerializationException e) {
            e.printStackTrace();
            logger.error("Failed to send UsageRecord=[{}] due to : {}", usageRecord, e.getMessage());
        }
    }
}
