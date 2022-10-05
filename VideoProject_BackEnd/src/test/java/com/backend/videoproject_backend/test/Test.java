package com.backend.videoproject_backend.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class Test {

    @Autowired
    private RedisTemplate redisTemplate;

    @org.junit.jupiter.api.Test
    public void t(){
        redisTemplate.opsForValue().set("k1","k1");
        System.out.println(redisTemplate.opsForValue().get("k1"));
    }
}
