package com.project.test.tech_serv.service.bisinessService.impl;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.DTO.entity.PasportDTO;
import com.project.test.tech_serv.service.EmployeeService;
import com.project.test.tech_serv.service.PasportService;
import com.project.test.tech_serv.service.bisinessService.EmployeBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor



public class EmployeeBusinessServiceImpl implements EmployeBusinessService {
    private final EmployeeService employeeService;
    private final PasportService pasportService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private  String topicName;

    @Override
    public GetFullDataEmployeeResponse getFullDataEmployeeResponse(String firstName, boolean kafkaSend) {
        EmployeeDTO employeeDTO = employeeService.findByFirstName(firstName);
        PasportDTO pasportDTO = pasportService.findByFirstName(firstName);

        if (employeeDTO==null || pasportDTO==null) {
            log.warn("Данные не найдены {}",firstName);
            return GetFullDataEmployeeResponse.EMPTY;
        }

        GetFullDataEmployeeResponse getFullDataEmployeeResponse = new GetFullDataEmployeeResponse(employeeDTO, pasportDTO);
        if (!kafkaSend) {
            return getFullDataEmployeeResponse;
        }
        else {
            kafkaTemplate.send(topicName, getFullDataEmployeeResponse);
                log.debug("Сообщение отправилось в {}", topicName);
                return GetFullDataEmployeeResponse.EMPTY;
        }
    }
}
