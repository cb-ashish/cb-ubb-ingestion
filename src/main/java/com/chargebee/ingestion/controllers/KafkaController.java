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
            return "Topic created successfully";
        } catch (Exception e) {
            logger.info("Topic {} created successfully", topicName);
            return "Error creating topic: " + e.getMessage();
        }
    }
}
