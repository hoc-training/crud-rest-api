package com.hoc.training.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Country {

    private Long id;
    private String code;
    private String name;
}
