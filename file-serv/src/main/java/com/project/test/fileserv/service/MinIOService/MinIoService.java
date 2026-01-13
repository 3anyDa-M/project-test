package com.project.test.fileserv.service.MinIOService;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinIoService {


    private final MinioClient minioClient;

    ///  Проверка существования бакета и создание в случае его отсутствия
    @SneakyThrows
    public void createBucketIfNotExist(String bucketName)  {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        log.debug("Такой бакет существует");
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.debug("Создание Бакета в MinIo ");
        }

    }


    /// загрузка файла в бакет
    @SneakyThrows
    public void uploadStream(String bucketName, String objectName, ByteArrayOutputStream stream) {
        byte[] bytes = stream.toByteArray();
        try (InputStream is = new ByteArrayInputStream(bytes)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(is, bytes.length, -1)
                            .build()
            );
            log.debug("Загрузка файлов в Minio");
        }
    }



    /// Скачивание объекта из MinIo
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String fileName) {
        return minioClient.getObject(
                GetObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }


    /// Удаление объекта из MinIO
    @SneakyThrows
    public void deleteObject(String bucketName, String fileName) {
        minioClient.removeObject(RemoveObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileName)
                .build());
    }

}
