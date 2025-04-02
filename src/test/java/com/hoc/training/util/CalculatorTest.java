package com.hoc.training.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Test Class for Calculator")
public class CalculatorTest {

    private Calculator calculator = new Calculator();

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

        if(result != 10) {
            throw new RuntimeException("Gagal");
        }
    }

    @Test
    public void testSumSuccess3() {
        var result = calculator.sum(5, 5);
        assertEquals(10, result);
    }

    @Test
    @DisplayName("Ini error")
    public void testSumError() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.sum(5, 5);
        });
    }
}
