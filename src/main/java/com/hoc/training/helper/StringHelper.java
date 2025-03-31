package com.hoc.training.helper;

import org.springframework.stereotype.Component;

@Component
public class StringHelper {

    public boolean isPalindrome(String value) {
        String reverseValue = new StringBuilder(value).reverse().toString();

        return value.equals(reverseValue);
    }

    public boolean isSame(String value1, String value2) {

        return value1.equals(value2);
    }
}
