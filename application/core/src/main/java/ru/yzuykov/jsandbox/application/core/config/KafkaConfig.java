package ru.yzuykov.jsandbox.application.core.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRs;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic messageRequest() {
        return TopicBuilder.name("message_request")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic messageResponse() {
        return TopicBuilder.name("message_response")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean("producerConfigs")
    public Map<String, Object> producerConfigs(@Value("${kafka.bootstrap-servers:localhost:9092}") String bootstrapServer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean("consumerConfigs")
    public Map<String, Object> consumerConfigs(@Value("${kafka.bootstrap-servers:localhost:9092}") String bootstrapServer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "123");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return props;
    }

    @Bean("defaultKafkaProducerFactory")
    public ProducerFactory<String, EventRq> producerFactory(@Qualifier("producerConfigs") Map<String, Object> producerConfigs) {
        return new DefaultKafkaProducerFactory<>(producerConfigs);
    }

    @Bean
    public KafkaTemplate<String, EventRq> kafkaTemplate(@Qualifier("defaultKafkaProducerFactory") ProducerFactory<String, EventRq> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean("kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EventRs>> kafkaListenerContainerFactory(
            @Qualifier("defaultKafkaConsumerFactory") ConsumerFactory<String, EventRs> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, EventRs> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);
        return factory;
    }

    @Bean("defaultKafkaConsumerFactory")
    public ConsumerFactory<String, EventRs> consumerFactory(@Qualifier("consumerConfigs") Map<String, Object> consumerConfigs) {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs);
    }
}
