package com.project.test.tech_serv.service.bisinessService.impl;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.service.EmployeeCacheService;
import com.project.test.tech_serv.service.bisinessService.EmployeBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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

    public void kafkaSend(GetFullDataEmployeeResponse response) {
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(topicName, key, response);
        log.debug("Сообщение отправлено в Kafka с ключом {}", key);
    }
}

