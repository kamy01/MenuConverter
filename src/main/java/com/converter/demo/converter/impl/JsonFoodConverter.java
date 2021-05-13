package com.converter.demo.converter.impl;

import com.converter.demo.converter.FoodConverter;
import com.converter.demo.model.Food;
import com.converter.demo.model.Menu;
import com.converter.demo.model.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.converter.demo.file.InputStreamSelector.getInputStreamFrom;
import static com.converter.demo.util.MapperUtils.getMapper;


@Service
@Slf4j
public class JsonFoodConverter implements FoodConverter {

    @Override
    public String getType() {
        return "json";
    }

    @Override
    public List<Food> convert(String fileName) {
        List<Food> foods = new ArrayList<>();
        var inputStream = getInputStreamFrom(fileName);
        var root = readRootFromInputStream(inputStream);

        Optional.ofNullable(root)
                .map(Root::getBreakFast)
                .map(Menu::getFood).
                ifPresent(foods::addAll);

        return foods;
    }

    private Root readRootFromInputStream(InputStream inputStream) {
        Root root;
        try {
            root = getMapper().readValue(inputStream, Root.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Error while converting from JSON", e);
        }
        return root;
    }
}
