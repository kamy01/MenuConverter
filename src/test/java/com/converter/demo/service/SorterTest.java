package com.converter.demo.service;

import com.converter.demo.model.Food;
import com.converter.demo.model.enums.SortOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SorterTest {

    private List<Food> foods = new ArrayList<>();

    @Autowired
    SorterService serviceSort;

    @BeforeEach
    private void beforeEach() {
        foods.add(new Food("Belgian Waffles", "21.4$", "Belgian desc", 222));
        foods.add(new Food("Homestyle Breakfas", "23.4$", "Homestyle desc", 2122));
        foods.add(new Food("waffle", "2222.4$", "What desc", 22222));
        foods.add(new Food("apple", "23.4$", "apple desc", 22));

    }

    @AfterEach
    private void after() {
        foods.clear();
    }

    @Test
    void givenFoodsAndSortAscFirstElementIsApple() {
        serviceSort.sort(foods, SortOption.ASC);
        assertThat(foods.get(0).getName()).isEqualTo("apple");
    }

    @Test
    void givenFoodsAndSortDescFirstElementIsWaffle() {
        serviceSort.sort(foods, SortOption.DESC);
        assertThat(foods.get(0).getName()).isEqualTo("waffle");
    }
}
