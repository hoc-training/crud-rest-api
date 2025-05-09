package com.hoc.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hoc.training.listener.BookListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableScheduling
@EnableRedisRepositories
@EnableCaching
public class CrudRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRestApiApplication.class, args);
	}

	public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory connectionFactory, BookListener bookListener) {
		var container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(bookListener, new ChannelTopic("books"));
		
		return container;
	}
}
