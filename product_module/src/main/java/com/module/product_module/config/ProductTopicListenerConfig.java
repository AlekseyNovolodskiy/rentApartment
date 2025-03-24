package com.module.product_module.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.product_module.model.dto.UserProductIntegrationDto;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.module.product_module.config.KafkaProductConsumerConfig.GROUP_ID;

@Configuration
@Getter
public class ProductTopicListenerConfig {

    private final List<UserProductIntegrationDto> messagesList = new CopyOnWriteArrayList<>();


    @KafkaListener(topics = "product_topic", groupId = GROUP_ID)
    public void messagesUpdate(String message) {


        ObjectMapper objectMapper = new ObjectMapper();
        try {

            UserProductIntegrationDto userProductIntegrationDto = objectMapper.readValue(convertToJson(message), UserProductIntegrationDto.class);
            messagesList.add(userProductIntegrationDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    private String convertToJson(String message) {
        if (message.startsWith("\"") && message.endsWith("\"")) {
            message = message.substring(1, message.length() - 1);
            message = message.replace("\\\"", "\"");
            return message;
        }
        throw new RuntimeException("Не удалось преобразовать строку");
    }
}

