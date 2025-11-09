package com.project.test.fileserv.service.kafkaListenerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.fileserv.model.ProcessedKeyEntity;
import com.project.test.fileserv.repository.ProcessedKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMassageListener {
    private final ObjectMapper objectMapper;

    private final ProcessedKeyRepository processedKeyRepository;

    @KafkaListener(topics = "tech-topic", groupId = "group1",containerFactory = "kafkaListenerContainerFactory")
    public void listenTechTopic(ConsumerRecord<String, byte[]> record) {
        String idenpotensyKey = record.key();

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
                log.warn("Header 'type' не найден, невозможно десериализовать");
                return;
            }
            Class<?> clazz = Class.forName(typeName);
            Object obj = objectMapper.readValue(record.value(), clazz);

            log.info("Получено сообщение типа {} : {}", typeName, obj);


            /// дальнеяшаяя логика работы с obj
            /// нужно создать сервис для обработки obj и превращения его в exel через JasperReports
            /// и дальнейшая отправка в другой микросервис для помещения в MinIO
            ///  либо сделать отдельный сервис для работы с MinIO  в этом микросервисе



            processedKeyRepository.save(new ProcessedKeyEntity(idenpotensyKey));
            log.info("Ключ {} записан в БД", idenpotensyKey);



        }
        catch (Exception e){
            log.error("Ошибка при десериализации сообщения в Kafak {}",e);
        }


    }
}
