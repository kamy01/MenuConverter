package com.converter.demo.converter;

import com.converter.demo.model.Food;

import java.util.List;

public interface FoodConverter {
    String getType();

    List<Food> convert(String fileName);
}
