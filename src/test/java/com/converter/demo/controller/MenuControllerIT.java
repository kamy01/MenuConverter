package com.converter.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MenuControllerIT {

    @Autowired
    MenuController menuController;


    @Test
    void withInvalidFileNameThenExceptionIsThrown() {
        assertThatThrownBy(() -> menuController.convertAndSort("menu.lml", "ASC"))
                .hasMessageContaining("Unsupported file name. Expecting menu.json/menu.xml");

    }

    @Test
    void withInvalidSortOrderThenExceptionIsThrown() {
        assertThatThrownBy(() -> menuController.convertAndSort("menu.lml", "aa"))
                .hasMessageContaining("Invalid sort option. Expecting ASC/DESC");

    }

    @Test
    void withValidXmlFileNameAndSortOrderNoExceptionsIsThrown() {
        assertDoesNotThrow(() -> menuController.convertAndSort("menu.xml", "ASC"));
    }

    @Test
    void withValidJsonFileNameAndSortOrderNoExceptionsIsThrown() {
        assertDoesNotThrow(() -> menuController.convertAndSort("menu.json", "ASC"));
    }

    @Test
    void withValidJsonFileNameAndAscSortTheOrderIsCorrect() {
        var foodList = menuController.convertAndSort("menu.json", "ASC");
        assertThat(foodList.get(0).getName()
                .compareToIgnoreCase(foodList.get(1).getName()))
                .isNegative();
    }

    @Test
    void withValidJsonFileNameAndDescSortTheOrderIsCorrect() {
        var foodList = menuController.convertAndSort(" menu.json", "  DESC  ");
        assertThat(foodList.get(0).getName()
                .compareToIgnoreCase(foodList.get(1).getName()))
                .isPositive();
    }

    @Test
    void withValidXmlFileNameAndAscSortTheOrderIsCorrect() {
        var foodList = menuController.convertAndSort("menu.xml ", " ASC ");
        assertThat(foodList.get(0).getName()
                .compareToIgnoreCase(foodList.get(1).getName()))
                .isNegative();
    }

    @Test
    void withValidXmlFileNameAndDescSortTheOrderIsCorrect() {
        var foodList = menuController.convertAndSort("menu.xml", "DESC");
        assertThat(foodList.get(0).getName()
                .compareToIgnoreCase(foodList.get(1).getName()))
                .isPositive();
    }
}
