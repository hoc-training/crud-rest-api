package com.hoc.training.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class Sample2Test {

    private int counter = 0;
    private Calculator calculator = new Calculator();

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

    @Order(2)
    @DisplayName("Test repeated")
    @RepeatedTest(
        value = 10,
        name = "{displayName} ke {currentRepetition} dari {totalRepetitions}"
    )
    void test3() {
        counter++;
        System.out.println(counter);
    }

    @DisplayName("Test repeated info")
    @RepeatedTest(
        value = 10, name = "{displayName}"
    )
    void test4(TestInfo testInfo, RepetitionInfo info) {
        counter++;
        System.out.println(counter);
        System.out.println(testInfo.getDisplayName() +" "+ info.getCurrentRepetition() + " and " + info.getTotalRepetitions());
    }

    @ParameterizedTest(name = "dengan parameter {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testWithParameter(int param) {
        var temp = param + param;
        var result = calculator.sum(param, param);

        Assertions.assertEquals(temp, result);
    }

    public static List<Integer> listSource() {
        return List.of(1, 2, 3, 4, 50);
    }

    @ParameterizedTest(name = "dengan paramter {0}")
    @MethodSource({"listSource"})
    void testWithMethodSource(Integer param) {
        var temp = param + param;
        var result = calculator.sum(param, param);

        Assertions.assertEquals(temp, result);
    }

    @Test
    @Disabled
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testSucessTimeout() throws InterruptedException {
        Thread.sleep(10000);
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testFailTimeout() {
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testParallel1() throws InterruptedException {
        Thread.sleep(4_000);
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testParallel2() throws InterruptedException {
        Thread.sleep(4_000);
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testParallel3() throws InterruptedException {
        Thread.sleep(4_000);
    }
}
