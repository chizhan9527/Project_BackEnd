package com.backend.videoproject_backend;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication(scanBasePackages = "com.backend.videoproject_backend")
@EntityScan("com.backend.videoproject_backend.dto")//扫描实体类
@EnableJpaRepositories("com.backend.videoproject_backend.dao")//扫描dao
public class VideoProjectBackEndApplication {

    public static void main(String[] args)  throws JsonProcessingException {
        SpringApplication.run(VideoProjectBackEndApplication.class, args);
    }

}
