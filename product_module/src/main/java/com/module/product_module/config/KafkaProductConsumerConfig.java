package com.module.product_module.config;

import com.module.product_module.model.dto.UserProductIntegrationDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProductConsumerConfig {

    public static final String KAFKA_BROKER = "localhost:29092";

    public static final String GROUP_ID = "kafka-product-box";
    @Bean
    public Map<String,Object> consumerConfiguration (){
        Map<String,Object> configProductMap =new HashMap<>();
        configProductMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configProductMap.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
        configProductMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProductMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return configProductMap;
    }
    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfiguration());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerFactory(){

        ConcurrentKafkaListenerContainerFactory<String, String> kafkaFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaFactory.setConsumerFactory(consumerFactory());

        return kafkaFactory;
    }
}
