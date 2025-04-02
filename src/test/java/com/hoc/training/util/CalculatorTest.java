package com.hoc.training.util;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

// @DisplayName("Test Class for Calculator")
@DisplayNameGeneration(SimpleDisplayNameGenerator.class)
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    // @DisplayName("Test for success method sum(a, b)")
    public void testSumSuccess() {
        calculator.sum(5, 5);
    }

    @Test
    // @DisplayName("Test for success method substract(a, b)")
    public void testSubtractSuccess() {
        calculator.substract(5, 5);
    }
}
