package com.example.consumer;

import com.example.consumer.controller.ChartController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ConsumerApplicationTests {
    @Autowired
    ChartController chartController;

    @Test
    public void test() {
        chartController.addChart("random", "20220202", "20220303", "day", "pie");

        Assertions.assertEquals(4, 4);

    }
}

