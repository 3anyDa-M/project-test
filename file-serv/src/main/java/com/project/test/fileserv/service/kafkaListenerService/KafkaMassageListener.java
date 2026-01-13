package com.project.test.fileserv.service.kafkaListenerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.fileserv.model.ProcessedKeyEntity;
import com.project.test.fileserv.repository.ProcessedKeyRepository;
import com.project.test.fileserv.service.MinIOService.MinIoService;
import com.project.test.fileserv.service.reportService.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMassageListener {
    private final ObjectMapper objectMapper;

    private final MinIoService minIoService;
    private final ProcessedKeyRepository processedKeyRepository;
    private final ExcelService excelService;
    private static final String BUCKET_MINIO = "tech-bucket";

    @KafkaListener(topics = "tech-topic", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    public void listenTechTopic(ConsumerRecord<String, byte[]> record) {
        String idenpotensyKey = record.key();
        if (idenpotensyKey == null) {
            log.warn("Ключ сообщения null, игнорируем");
            return;
        }

        if (processedKeyRepository.existsById(idenpotensyKey)) {
            log.info("Сообщение {} уже обработано", idenpotensyKey);
            return;
        }

        try {
            String typeName = null;
            if (record.headers() != null) {
                Header typeHeader = record.headers().lastHeader("type");
                if (typeHeader != null) {
                    typeName = new String(typeHeader.value(), "UTF-8");
                }
            }
            if (typeName == null) {
                log.warn("Header 'type' не найден, невозможно десcериализовать");
                return;
            }

            Class<?> clazz = Class.forName(typeName);
            Object obj = objectMapper.readValue(record.value(), clazz);
            log.info("Получено сообщение типа {} : {}", typeName, obj);

            String fileName = "report-" + idenpotensyKey + ".xlsx";
            ByteArrayOutputStream excelStream = excelService.createExcel(obj);
            log.debug("Ошибка при создании  {} : {} ", fileName, excelStream);


            minIoService.createBucketIfNotExist(BUCKET_MINIO);

            minIoService.uploadStream(BUCKET_MINIO, fileName, excelStream);

            processedKeyRepository.save(new ProcessedKeyEntity(idenpotensyKey));
            log.info("Ключ {} записан в БД", idenpotensyKey);
        } catch (Exception e) {
            log.error("Ошибка при обработке сообщения в Kafka", e);

        }


    }
}
