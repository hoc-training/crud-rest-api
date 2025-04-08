package com.hoc.training.util;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Tags({
        @Tag("sample-test")
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SampleTest {

    @Test
    @Order(3)
    public void test1() {

    }

    @Test
    @Order(2)
    public void test2() {

    }

    private String temp;

    @Test
    @Order(4)
    public void testA() {
        temp = "A";
        System.out.println(temp);
    }

    @Test
    @Order(1)
    public void testB() {
        System.out.println(temp);
    }
}
