package com.hoc.training.redisrepository;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.TimeToLive;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@KeySpace("books")
public class Book implements Serializable {
    @Id
    private String id;

    private String title;

    private Long price;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long ttl = -1L;
}
