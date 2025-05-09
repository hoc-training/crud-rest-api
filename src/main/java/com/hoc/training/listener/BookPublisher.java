package com.hoc.training.listener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BookPublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void publish() {
        redisTemplate.convertAndSend("books", "Book published at " + UUID.randomUUID());
    }
}
