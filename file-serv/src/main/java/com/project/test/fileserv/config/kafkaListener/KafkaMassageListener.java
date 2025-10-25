package com.project.test.fileserv.config.kafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.fileserv.model.Dto.tech_serv.GetFullDataEmployeeResponse;
import com.project.test.fileserv.model.ProcessedKeyEntity;
import com.project.test.fileserv.repository.ProcessedKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMassageListener {
    private final ObjectMapper objectMapper;
    private final ProcessedKeyRepository processedKeyRepository;
    @KafkaListener(topics = "tech-topic", groupId = "group1")
    public void listenTechTopic(ConsumerRecord<String, GetFullDataEmployeeResponse> record) {

        String idenpotensyKey = record.key();
        if (processedKeyRepository.existsById(idenpotensyKey)) {
            log.info("Сообщение {} уже обработано", idenpotensyKey);
            return;
        }
        log.info("Получено сообщение: {}", record.value());
        processedKeyRepository.save(new ProcessedKeyEntity(idenpotensyKey));
        log.info("Ключ {} записан в БД", idenpotensyKey);

        /// Дальнейшая логика после получения
    }
}
