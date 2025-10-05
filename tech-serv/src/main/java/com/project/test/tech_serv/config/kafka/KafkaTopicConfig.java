package com.project.test.tech_serv.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic newTopic() {

        Map<String, String> config = new HashMap<>();
        config.put("cleanup.policy", "compact");
        config.put("retention.ms", "3600000");//Время хранения сообщений в миллисекундах
        config.put("delete.retention.ms", "112500");// Время хранения удалённых записей при compact /3 минуты

        return new NewTopic(topicName, 3, (short) 1);

    }

}
