package com.chargebee.ingestion.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public AdminClient kafkaAdminClient() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        return AdminClient.create(properties);
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(Collections.singletonMap(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        ));
    }

    @Bean
    public NewTopic usageRecordsTopic() {
        AdminClient adminClient = kafkaAdminClient();
        String topicName = "usage-records-topic";
        int numPartitions = 3;
        short replicationFactor = 2;

        try {
            // Check if the topic exists
            ListTopicsResult topics = adminClient.listTopics();
            Set<String> existingTopics = topics.names().get();

            // Create topic if not exists
            if (!existingTopics.contains(topicName)) {
                // Create topic
                return new NewTopic(topicName, numPartitions, replicationFactor);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to check or create Kafka topic", e);
        }
        return null;
    }
}
