package com.hoc.training.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Sample2Test {

    private int counter = 0;

    @BeforeAll
    void beforeAll() {

    }

    @AfterAll
    void afterAll() {

    }

    @Test
    @Order(1)
    void test2() {

        // PER_METHOD
        // Sample2Test test2 = new Sample2Test();
        // test2.test2();

        // Sample2Test test1 = new Sample2Test();
        // test1.test1();

        // Sample2Test test3 = new Sample2Test();
        // test3.test3();

        // PER_CLASS
        // Sample2Test test = new Sample2Test();
        // test.test1();
        // test.test2();
        // test.test3();

        counter++;
        System.out.println(counter);
    }

    @Test
    @Order(3)
    void test1() {
        counter++;
        System.out.println(counter);
    }

    @Test
    @Order(2)
    void test3() {
        counter++;
        System.out.println(counter);
    }
}
