package com.chargebee.ingestion.controllers;

import com.chargebee.ingestion.service.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTopicService kafkaTopicService;

    @PostMapping("/create-topic")
    public String createTopic(@RequestParam String topicName, @RequestParam int partitions, @RequestParam short replicationFactor) {
        try {
            logger.info("Received request to create topic: {}, partitions: {}, replicationFactor: {}", topicName, partitions, replicationFactor);
            logger.info("Broker - ", kafkaTopicService.getBootstrapServers());
            kafkaTopicService.createTopic(topicName, partitions, replicationFactor);
            logger.info("Topic {} creation successful", topicName);
            return String.format("Topic %s created successfully with partition %s and replication_factor %s",  topicName, partitions, replicationFactor);
        } catch (Exception e) {
            logger.info("Topic {} creation failed", topicName);
            return "Error creating topic: " + e.getMessage();
        }
    }
}
