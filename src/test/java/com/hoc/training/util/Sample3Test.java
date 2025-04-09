package com.hoc.training.util;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@DisplayName("Nested Queue")
public class Sample3Test {

    private Queue<String> queue;

    @Nested
    @DisplayName("New")
    public class WhenNew {
        
        @BeforeEach
        void setUp() {
            queue = new LinkedList<>();
        }

        @Test
        @DisplayName("New Data")
        void newData() {
            queue.offer("Test Data");
            Assertions.assertEquals(1, queue.size());
        }

        @Test
        @DisplayName("New Data Again")
        void moreNewData(TestInfo info) {
            System.out.println(info.getDisplayName());
            System.out.println(info.getTags());

            queue.offer("Test Data Again");
            queue.offer("Test Again");
            Assertions.assertEquals(2, queue.size());
        }
    }
}
