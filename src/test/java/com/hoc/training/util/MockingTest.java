package com.hoc.training.util;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockingTest {

    @Test
    void testMocking() {
        List<String> data = Mockito.mock(List.class);

        Mockito.when(data.get(0)).thenReturn("Test");
        Mockito.when(data.get(25)).thenReturn("Test 25");

        System.out.println(data.get(0));
        System.out.println(data.get(25));

        Mockito.verify(data, Mockito.times(1)).get(0);
    }
}
