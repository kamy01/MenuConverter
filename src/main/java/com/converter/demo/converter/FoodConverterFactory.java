package com.converter.demo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodConverterFactory {

    private static final Map<String, FoodConverter> converterCache = new HashMap<>();

    @Autowired
    public FoodConverterFactory(List<FoodConverter> foodConverters) {
        foodConverters.forEach(foodConverter -> converterCache.put(foodConverter.getType(), foodConverter));
    }

    public static FoodConverter getConverter(String type) {
        FoodConverter foodConverter = converterCache.get(type.toLowerCase());
        if (foodConverter == null) {
            throw new RuntimeException("Unknown food converter type: " + type);
        }
        return foodConverter;
    }
}
