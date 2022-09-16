package com.backend.videoproject_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.backend.videoproject_backend")
@EntityScan("com.backend.videoproject_backend.dto")//扫描实体类
@EnableJpaRepositories("com.backend.videoproject_backend.dao")//扫描dao
public class VideoProjectBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoProjectBackEndApplication.class, args);
    }

}
