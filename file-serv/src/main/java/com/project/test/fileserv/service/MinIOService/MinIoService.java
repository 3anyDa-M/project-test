package com.project.test.fileserv.service.MinIOService;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinIoService {


    private final MinioClient minioClient;

    ///  Проверка существования бакета и создание в случае его отсутствия
    @SneakyThrows
    public void createBucketIfNotExist(String bucketName){
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /// загрузка файла в бакет
    @SneakyThrows
    public void uploadFile(String bucketName, String fileName //String filePath
    ){
        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(fileName)
                        /// filename(filePath)
                        .build()
        );
    }


    /// Скачивание объекта из MinIo
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String fileName){
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
    public void deleteObject (String bucketName, String fileName){
        minioClient.removeObject(RemoveObjectArgs
                .builder()
                    .bucket(bucketName)
                    .object(fileName)
                .build());
    }

}
