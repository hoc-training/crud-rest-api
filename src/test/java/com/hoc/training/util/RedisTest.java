package com.hoc.training.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.data.redis.support.collections.RedisSet;
import org.springframework.data.redis.support.collections.RedisZSet;
import org.springframework.lang.Nullable;

import com.hoc.training.redisrepository.Book;
import com.hoc.training.redisrepository.BookRepository;
import com.hoc.training.redisrepository.BookService;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

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

    @Test
    void hash() {
        HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
        operations.put("item", "id", "ITEM001");
        operations.put("item", "name", "Item Name 1");
        operations.put("item", "email", "item1@mail.com");

        assertEquals("ITEM001", operations.get("item", "id"));
        assertEquals("Item Name 1", operations.get("item", "name"));
        assertEquals("item1@mail.com", operations.get("item", "email"));

        redisTemplate.opsForHash().put("items", "idx", "IDX001");
        redisTemplate.opsForHash().put("items", "s_name", "String Name");

        assertEquals("IDX001", operations.get("items", "idx"));
        assertEquals("String Name", operations.get("items", "s_name"));

        redisTemplate.delete("items");
        
        // using Map to store multiple items
        Map<String, Object> items = new HashMap<>();
        items.put("id", "ITEM002");
        items.put("name", "Item Name 2");
        items.put("email", "item2@mail.com");
        operations.putAll("item", items);

        assertEquals("ITEM002", operations.get("item", "id"));
        assertEquals("Item Name 2", operations.get("item", "name"));
        assertEquals("item2@mail.com", operations.get("item", "email"));
    }

    @Test
    void hyprLogLog() {
        HyperLogLogOperations <String, String> operations = redisTemplate.opsForHyperLogLog();
        operations.add("users:1", "user1", "user2", "user3");
        operations.add("users:1", "user1", "user4", "user5");
        operations.add("users:1", "user4", "user5", "user6");

        // count the number of unique users
        assertEquals(6L, operations.size("users:1"));
    }

    @Test
    void transaction() {
        redisTemplate.execute(new SessionCallback<Object>() {

            @Override
            @Nullable
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();

                operations.opsForValue().set("test:1", "Test 1", Duration.ofSeconds(2));
                operations.opsForValue().set("test:2", "Test 2", Duration.ofSeconds(2));

                operations.exec();
                return null;
            }
        });

        assertEquals("Test 1", redisTemplate.opsForValue().get("test:1"));
        assertEquals("Test 2", redisTemplate.opsForValue().get("test:2"));
    }

    @Test
    void pipeline() {
        List<Object> results = redisTemplate.executePipelined(new SessionCallback<Object>() {

            @Override
            @Nullable
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("ping:1", "Pong 1", Duration.ofSeconds(2));
                operations.opsForValue().set("ping:2", "Pong 2", Duration.ofSeconds(2));
                operations.opsForValue().set("ping:3", "Pong 3", Duration.ofSeconds(2));
                operations.opsForValue().set("ping:4", "Pong 4", Duration.ofSeconds(2));
                
                return null;
            }
        });

        assertThat(results, hasSize(4));
        assertThat(results, hasItem(true));
        assertThat(results, not((hasItem(false))));
    }

    @Test
    void publish() {
        StreamOperations<String, Object, Object> operations = redisTemplate.opsForStream();

        MapRecord<String, Object, Object> records = MapRecord.create("stream:1", Map.of(
            "obj:1", "Object 1",
            "obj:2", "Object 2"
        ));

        for(int i = 0; i < 10; i++) {
            operations.add(records);
        }
    }

    @Test
    void subscribe() {
        StreamOperations<String, Object, Object> operations = redisTemplate.opsForStream();

        try {
            operations.createGroup("stream:1", "sample-group");
        } catch (Exception e) {
            // TODO: handle exception
        }

        List<MapRecord<String, Object, Object>> records = operations.read(
            Consumer.from("sample-group", "sample:1"), 
            StreamOffset.create("stream:1", ReadOffset.lastConsumed()));
        
        for(MapRecord<String, Object, Object> record : records) {
            System.out.println(record);
        }
    }

    @Test
    void pubSub() {
        redisTemplate.getConnectionFactory().getConnection().subscribe(new MessageListener() {

            @Override
            public void onMessage(Message message, @Nullable byte[] pattern) {
                String event = new String(message.getBody());
                System.out.println("Message: " + event);
            }
        }, "my:channel".getBytes());

        redisTemplate.getConnectionFactory().getConnection().subscribe(new MessageListener() {

            @Override
            public void onMessage(Message message, @Nullable byte[] pattern) {
                String event = new String(message.getBody());
                System.out.println("Message: " + event);
            }
        }, "my:channel".getBytes());

        for(int i = 0; i < 10; i++) {
            redisTemplate.convertAndSend("my:channel", "Hi.. " + i);
        }
    }

    @Test
    void redisList() {
        List<String> list = RedisList.create("customers", redisTemplate);
        list.add("Budi");
        list.add("Budiman");
        list.add("Dorami");
        assertThat(list, hasItems("Budi", "Budiman", "Dorami"));

        List<String> result = redisTemplate.opsForList().range("customers", 0, -1);
        assertThat(result, hasItems("Budi", "Budiman", "Dorami"));
    }

    @Test
    void redisSet() {
        Set<String> set = RedisSet.create("roles", redisTemplate);
        set.addAll(Set.of("role", "admin", "satu"));
        set.addAll(Set.of("role", "admin", "dua"));
        set.addAll(Set.of("role", "admin", "tiga"));
        assertThat(set, hasItems("role", "admin", "satu", "dua", "tiga"));

        Set<String> result = redisTemplate.opsForSet().members("roles");
        assertThat(result, hasItems("role", "admin", "satu", "dua", "tiga"));
    }

    @Test
    void redisZSet() {
        RedisZSet<String> set = RedisZSet.create("rank", redisTemplate);
        set.add("Cahya", 100);
        set.add("Darma", 80);
        set.add("Dori", 90);
        assertThat(set, hasItems("Cahya", "Darma", "Dori"));

        Set<String> result = redisTemplate.opsForZSet().range("rank", 0, -1);
        assertThat(result, hasItems("Cahya", "Darma", "Dori"));

        assertEquals("Cahya", set.popLast());
        assertEquals("Dori", set.popLast());
        assertEquals("Darma", set.popLast());
    }

    @Test
    void redisMap() {
        Map<String, String> map = new DefaultRedisMap<>("country:1", redisTemplate);
        map.put("name", "England");
        map.put("code", "UK");
        assertThat(map, hasEntry("name", "England"));
        assertThat(map, hasEntry("code", "UK"));

        Map<Object, Object> result = redisTemplate.opsForHash().entries("country:1");
        assertThat(result, hasEntry("name", "England"));
        assertThat(result, hasEntry("code", "UK"));
    }

    @Test
    void redisRepository() {
        Book book = Book.builder()
            .id("1")
            .title("Book 1")
            .price(50_000L)
            .build();
        bookRepository.save(book);

        Map<Object, Object> map = redisTemplate.opsForHash().entries("books:1");
        assertEquals(book.getId(), map.get("id"));
        assertEquals(book.getTitle(), map.get("title"));
        assertEquals(book.getPrice().toString(), map.get("price"));

        Book book2 = bookRepository.findById("1").get();
        assertEquals(book, book2);
    }

    @Test
    void ttl() throws InterruptedException {
        Book book = Book.builder()
            .id("1")
            .title("Book 1")
            .price(50_000L)
            .ttl(3L)
            .build();
        bookRepository.save(book);

        assertTrue(bookRepository.findById("1").isPresent());

        Thread.sleep(Duration.ofSeconds(5).toMillis());
        assertFalse(bookRepository.findById("1").isPresent());
    }

    @Test
    void cache() {
        Cache cache = cacheManager.getCache("scores");
        cache.put("test:1", "Test 1");
        cache.put("test:2", "Test 2");

        assertEquals("Test 1", cache.get("test:1", String.class));
        assertEquals("Test 2", cache.get("test:2", String.class));

        // evict the cache
        cache.evict("test:1");
        cache.evict("test:2");
        assertNull(cache.get("test:1", String.class));
        assertNull(cache.get("test:2", String.class));
    }

    @Autowired
    private BookService bookService;

    @Test
    void cacheable() {
        Book book = bookService.getBook("1");
        assertEquals("1", book.getId());

        Book book2 = bookService.getBook("1");
        assertEquals(book, book2);
    }

    @Test
    void cachePut() {
        Book book = Book.builder().id("2").title("Book 2").price(150L).build();
        bookService.save(book);

        Book book2 = bookService.getBook("2");
        assertEquals(book, book2);
    }

    @Test
    void remove() {
        Book book = bookService.getBook("3");
        assertNotNull(book);

        bookService.remove("3");

        Book book2 = bookService.getBook("3");
        assertEquals(book, book2);
    }
}
