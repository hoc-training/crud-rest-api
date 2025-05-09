package com.hoc.training.redisrepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BookService {

    @Cacheable(value = "books", key = "#id")
    public Book getBook(String id) {
        log.info("Get book {}", id);
        return Book.builder().id(id).title("Book 1").price(100L).build();   
    }

    @CachePut(value = "books", key = "#book.id")
    public Book save(Book book) {
        log.info("Save book {}", book);
        return book;
    }

    @CacheEvict(value = "books", key = "#id")
    public void remove(String id) {
        log.info("Remove book {}", id);
    }
}
