package com.hoc.training.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.opentest4j.TestAbortedException;

@DisplayName("Test Class for Calculator")
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @BeforeEach
    public void beforeProccess() {
        System.out.println("Before Proccess");
    }

    @AfterEach
    public void afterProccess() {
        System.out.println("After Proccess");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }

    @Test
    @DisplayName("Test for success method sum(a, b)")
    public void testSumSuccess() {
        calculator.sum(5, 5);
    }

    @Test
    @DisplayName("Test for success method substract(a, b)")
    public void testSubtractSuccess() {
        calculator.substract(5, 5);
    }

    @Test
    @DisplayName("Test for success method 2 substract(a, b)")
    @Disabled
    public void testSubtractSuccess2() {
        calculator.substract(5, 5);
    }

    @Test
    @DisplayName("Test for success method 2 sum(a, b)")
    public void testSumSuccess2() {
        var result = calculator.sum(5, 5);

        if (result != 10) {
            throw new RuntimeException("Gagal");
        }
    }

    @Test
    @DisplayName("Test for success method 3 sum(a, b)")
    public void testSumSuccess3() {
        var result = calculator.sum(5, 5);
        assertEquals(10, result);
    }

    @Test
    @Disabled
    @DisplayName("Test for fail method 1 sum(a, b)")
    public void testSumFail() {
        var result = calculator.sum(5, 5);
        assertEquals(20, result);
    }

    @Test
    @DisplayName("Ini error")
    public void testSumError() {
        assertThrows(NullPointerException.class, () -> {
            calculator.sum(5, null);
        });
    }

    @Test
    public void manualAborted() {
        var profile = System.getenv("DEBUG");
        if (!"WARN".equals(profile)) {
            throw new TestAbortedException("Tes aborted");
        }
    }

    @Test
    public void assumption() {
        assumeTrue("WARN".equals(System.getenv("DEBUG")));
    }

    @Test
    @EnabledOnOs({ OS.WINDOWS })
    public void windowsOnly() {

    }

    @Test
    @EnabledOnOs(OS.LINUX)
    public void linuxOnly() {

    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    public void onlyJava17() {

    }

    @Test
    @DisabledOnJre(value = { JRE.JAVA_17 })
    public void disabledOnJava17() {

    }

    @Test
    @DisabledOnJre(value = JRE.JAVA_21)
    public void onlyJava21() {

    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_21)
    public void onJava17ToJava21() {

    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
    public void onJava8ToJava11() {

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "WARN")
    public void debugEnbaled() {

    }

    @Test
    @DisabledIfEnvironmentVariable(named = "DEBUG", matches = "WARN")
    public void debugDisabled() {

    }
}
