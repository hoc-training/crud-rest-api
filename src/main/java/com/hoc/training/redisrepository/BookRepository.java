package com.hoc.training.redisrepository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends KeyValueRepository<Book, String> {
    // custom query methods can be defined here if needed
}
