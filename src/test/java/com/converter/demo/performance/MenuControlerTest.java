package com.converter.demo.performance;

import com.converter.demo.controller.MenuController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;

@SpringBootTest
@Slf4j
class MenuControlerTest {

    @Autowired
    MenuController userInteraction;

    @Test
    void speedTest() {
        Instant start = Instant.now();
        for (int i = 0; i < 100; i++) {
            userInteraction.convertAndSort("menu.json", "asc");
        }
        Instant end = Instant.now();

        log.debug("Processed 100 calls in " + Duration.between(start, end));
    }

}
