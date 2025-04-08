package com.hoc.training.util;

public class Calculator {

    public Integer sum(Integer a, Integer b) {
        return a + b;
    }

    public Integer substract(Integer a, Integer b) {
        if (a == null || b == null) {
            throw new NullPointerException("Can not null");
        } else {
            return a - b;
        }
    }
}
