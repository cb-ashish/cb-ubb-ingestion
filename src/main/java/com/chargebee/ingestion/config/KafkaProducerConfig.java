package com.chargebee.ingestion.config;

import com.amazonaws.services.schemaregistry.common.AWSSchemaNamingStrategy;
import com.amazonaws.services.schemaregistry.serializers.avro.AWSKafkaAvroSerializer;
import com.chargebee.ingestion.models.UsageRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.security.protocol}")
    private String kafkaSecurityProtocol;

    @Value("${kafka.sasl.mechanism}")
    private String kafkaSaslMechanism;

    @Bean
    public ProducerFactory<String, UsageRecord> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put("security.protocol", kafkaSecurityProtocol);
        configProps.put("sasl.mechanism", kafkaSaslMechanism);
        configProps.put("sasl.jaas.config", "software.amazon.msk.auth.iam.IAMLoginModule required;");
        configProps.put("sasl.client.callback.handler.class", "software.amazon.msk.auth.iam.IAMClientCallbackHandler");

        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AWSKafkaAvroSerializer.class.getName());
        configProps.put("schema.registry.url", "https://vpce-073db7a998a29098f-spv7tq7b.glue.us-east-1.vpce.amazonaws.com");
        configProps.put("aws.region", "us-east-1");
        configProps.put("registry.name", "default"); // This should match your registry name

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, UsageRecord> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
