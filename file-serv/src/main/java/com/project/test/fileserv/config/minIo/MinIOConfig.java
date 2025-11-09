package com.project.test.fileserv.config.minIo;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfig {
    @Value("${spring.minio.access-key}")
    private String minioAccessKey;
    @Value("${spring.minio.secret-key}")
    private String minioSecretKey;
    @Value("${spring.minio.url}")
    private String minioUrl;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioAccessKey, minioSecretKey)
                .build();
    }

}
