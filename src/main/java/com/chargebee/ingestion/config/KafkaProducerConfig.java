package com.chargebee.ingestion.config;

import com.amazonaws.services.costandusagereport.model.AWSRegion;
import com.amazonaws.services.glue.model.Compatibility;
import com.amazonaws.services.schemaregistry.serializers.avro.AWSKafkaAvroSerializer;
import com.amazonaws.services.schemaregistry.utils.AWSSchemaRegistryConstants;
import com.amazonaws.services.schemaregistry.utils.AvroRecordType;
import com.chargebee.ingestion.models.UsageRecord;
import com.chargebee.ingestion.service.UsageRecordProducer;
import org.apache.avro.Schema;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerConfig.class);
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.security.protocol}")
    private String kafkaSecurityProtocol;

    @Value("${kafka.sasl.mechanism}")
    private String kafkaSaslMechanism;

    @Value("${kafka.schema.registry-url}")
    private String schemaRegistryUrl;

    @Value("${spring.profiles.active}")
    private String profile;
    @Bean
    public ProducerFactory<String, UsageRecord> producerFactory() throws IOException {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put("security.protocol", kafkaSecurityProtocol);
        configProps.put("sasl.mechanism", kafkaSaslMechanism);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AWSKafkaAvroSerializer.class.getName());
        logger.info("Producer profile {}", profile);
        if (!profile.equals("local")) {
            configProps.put("sasl.jaas.config", "software.amazon.msk.auth.iam.IAMLoginModule required;");
            configProps.put("sasl.client.callback.handler.class", "software.amazon.msk.auth.iam.IAMClientCallbackHandler");
        }

        configProps.put("value.converter.registry.name", "default");
        configProps.put("value.converter.compatibility", "BACKWARD");
        configProps.put("value.converter.schemas.enable", true);
        configProps.put("value.converter.schemaAutoRegistrationEnabled", true);
        configProps.put("value.converter.endpoint", schemaRegistryUrl);
        configProps.put("value.converter.region", "us-east-1");
        configProps.put("value.converter.avroRecordType", "GENERIC_RECORD");

        logger.info("Producer Creation properties {}", configProps);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, UsageRecord> kafkaTemplate() throws IOException {
        return new KafkaTemplate<>(producerFactory());
    }
}
