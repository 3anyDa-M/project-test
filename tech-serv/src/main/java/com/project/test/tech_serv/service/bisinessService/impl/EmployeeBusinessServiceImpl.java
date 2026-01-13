package com.project.test.tech_serv.service.bisinessService.impl;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.service.interfaceService.EmployeeCacheService;
import com.project.test.tech_serv.service.bisinessService.EmployeBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor


public class EmployeeBusinessServiceImpl implements EmployeBusinessService {
    private final EmployeeCacheService employeeCacheService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;


    @Override
    public GetFullDataEmployeeResponse getFullDataEmployeeResponse(String firstName, boolean kafkaSend) {
        GetFullDataEmployeeResponse response = employeeCacheService.getEmployeeData(firstName);

        if (kafkaSend && response != GetFullDataEmployeeResponse.EMPTY) {
            kafkaSend(response);
            return GetFullDataEmployeeResponse.EMPTY;
        }
        return response;
    }

    private void kafkaSend(GetFullDataEmployeeResponse response) {
        String key = UUID.randomUUID().toString();
        Message<GetFullDataEmployeeResponse> massage = MessageBuilder.withPayload(response)
                .setHeader(KafkaHeaders.KEY, key)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader("type", GetFullDataEmployeeResponse.class.getName())
                .build();
        kafkaTemplate.send(massage);
        log.debug("Сообщение отправлено в Kafka с ключом {}", key);
    }
}

