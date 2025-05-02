package com.hoc.training.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void redisTemplate() {
        assertNotNull(redisTemplate);
    }

    @Test
    void string() throws InterruptedException {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set("name", "Test 1", Duration.ofSeconds(2));
        assertEquals("Test 1", valueOperations.get("name"));

        Thread.sleep(Duration.ofSeconds(3).toMillis());
        assertNull(valueOperations.get("name"));
    }

    @Test
    void list() {
        ListOperations<String, String> operations = redisTemplate.opsForList();
        operations.rightPush("class", "Satu");
        operations.rightPush("class", "Dua");
        operations.rightPush("class", "Tiga");

        assertEquals("Satu", operations.leftPop("class"));
        assertEquals("Dua", operations.leftPop("class"));
        assertEquals("Tiga", operations.leftPop("class"));
    }

    @Test
    void set() {
        SetOperations<String, String> operations = redisTemplate.opsForSet();
        operations.add("classes", "Satu", "Dua", "Tiga", "Satu");
        assertEquals(3, operations.size("classes"));
        assertEquals(3, operations.members("classes").size());

        Set<String> classes = operations.members("classes");
        assertThat(classes, hasItems("Satu", "Dua", "Tiga"));

        operations.add("books", "Book 1");
        operations.add("books", "Book 1");
        operations.add("books", "Book 2");
        operations.add("books", "Book 2");
        operations.add("books", "Book 3");
        operations.add("books", "Book 3");
        assertEquals(3, operations.size("books"));

        Set<String> books = operations.members("books");
        assertThat(books, hasItems("Book 1", "Book 2", "Book 3"));
    }

    @Test
    void zSet() {
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        operations.add("price", "Book 1", 300);
        operations.add("price", "Book 2", 100);
        operations.add("price", "Book 3", 200);

        assertEquals("Book 1", operations.popMax("price").getValue());
        assertEquals("Book 3", operations.popMax("price").getValue());
        assertEquals("Book 2", operations.popMax("price").getValue());
    }
}
