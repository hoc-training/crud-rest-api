package com.hoc.training.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BookListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // handle the message received from the Redis stream
        log.info("Received message: {}", new String(message.getBody()));
    }   

}
