package com.first.function_module.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaTopics {
    PRODUCT("product_topic"),
    EMAIL_SENDER("sender_topic");

    private final String topicName;
}
