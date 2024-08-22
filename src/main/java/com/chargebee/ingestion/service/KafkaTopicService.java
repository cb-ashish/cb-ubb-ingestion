package com.chargebee.ingestion.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaTopicService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicService.class);
    private static final int RETRY_DELAY_MS = 5000; // Delay between retries
    private static final int MAX_RETRIES = 10; // Maximum number of retries

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void createTopic(String topicName, int partitions, short replicationFactor) throws Exception {
        logger.info("Checking if topic '{}' already exists...", topicName);

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        logger.debug("Kafka AdminClient configuration: {}", properties);

        try (AdminClient adminClient = AdminClient.create(properties)) {
            if (isTopicExists(adminClient, topicName)) {
                logger.info("Topic '{}' already exists. Skipping creation.", topicName);
                return;
            }

            logger.info("Topic '{}' does not exist. Proceeding with creation.", topicName);

            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
            logger.info("Creating topic: {}", newTopic);

            adminClient.createTopics(Collections.singleton(newTopic)).all().get();
            logger.info("Topic creation initiated successfully: {}", topicName);

            // Wait for the topic to be created
            waitForTopicToBeCreated(adminClient, topicName);
        } catch (Exception exception) {
            logger.error("Failed to create topic: {}. Error: {}", topicName, exception.getMessage(), exception);
            throw exception;  // Optional: rethrow the exception if you want it to propagate
        }
    }

    private boolean isTopicExists(AdminClient adminClient, String topicName) throws Exception {
        try {
            ListTopicsResult listTopicsResult = adminClient.listTopics();
            boolean exists = listTopicsResult.names().get().contains(topicName);
            if (exists) {
                logger.debug("Topic '{}' exists in Kafka.", topicName);
            } else {
                logger.debug("Topic '{}' does not exist in Kafka.", topicName);
            }
            return exists;
        } catch (Exception e) {
            logger.error("Error checking if topic '{}' exists: {}", topicName, e.getMessage(), e);
            throw new RuntimeException("Error while checking topic existence", e);
        }
    }

    private void waitForTopicToBeCreated(AdminClient adminClient, String topicName) throws InterruptedException {
        int retryCount = 0;
        boolean topicCreated = false;

        while (retryCount < MAX_RETRIES && !topicCreated) {
            try {
                ListTopicsResult listTopicsResult = adminClient.listTopics();
                topicCreated = listTopicsResult.names().get().contains(topicName);

                if (topicCreated) {
                    logger.info("Topic '{}' has been created successfully", topicName);
                } else {
                    logger.info("Topic '{}' not yet created. Waiting for {} ms before retrying...", topicName, RETRY_DELAY_MS);
                    Thread.sleep(RETRY_DELAY_MS);
                    retryCount++;
                }
            } catch (Exception e) {
                logger.error("Error checking topic creation status for '{}': {}", topicName, e.getMessage(), e);
                throw new RuntimeException("Error while waiting for topic creation", e);
            }
        }

        if (!topicCreated) {
            logger.error("Topic '{}' was not created after {} retries", topicName, MAX_RETRIES);
            throw new RuntimeException("Failed to create topic after retries");
        }
    }
}
