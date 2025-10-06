package com.project.test.fileserv.config.kafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.fileserv.model.Dto.tech_serv.GetFullDataEmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMassadeListener {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "tech-topic", groupId = "group1")
    public void listenTechTopic(String message) {
        try {
            log.info("Получено сообщение: {}", message);
            GetFullDataEmployeeResponse employee = parse(message, GetFullDataEmployeeResponse.class);
            log.info("Десериализованный объект: {}", employee);

            /// Дальнейшая логика после получения



        }catch (Exception e){
            log.debug("Failed to parse Kafka message: " + message, e);
        }

    }





    /// общий парсер сообщений
    private <T> T parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse Kafka message: " + json, e);
        }
    }

}
