package com.chargebee.ingestion.service;

import com.chargebee.ingestion.controllers.KafkaController;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaTopicService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicService.class);
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void createTopic(String topicName, int partitions, short replicationFactor) throws Exception {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(properties)) {
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
            adminClient.createTopics(Collections.singleton(newTopic)).all().get();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}
