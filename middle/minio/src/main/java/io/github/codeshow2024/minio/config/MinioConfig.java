package io.github.codeshow2024.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio配置文件
 */
@Configuration
public class MinioConfig {
    @Value("${minio.upload.username}")
    private String username;
    @Value("${minio.upload.password}")
    private String password;
    @Value("${minio.upload.url}")
    private String url;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(username, password)
                .build();
    }
}
