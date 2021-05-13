package com.converter.demo.controller;

import com.converter.demo.model.Food;
import com.converter.demo.service.ConverterService;
import com.converter.demo.service.SorterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.converter.demo.validator.SortOrderValidator.validateAndGetSortOption;

@Controller
public class MenuController {

    private ConverterService converterService;
    private SorterService sorterService;

    @Autowired
    public MenuController(ConverterService converterService, SorterService sorterService) {
        this.converterService = converterService;
        this.sorterService = sorterService;
    }

    public List<Food> convertAndSort(String filename, String sortOption) {
        var sortEnum = validateAndGetSortOption(sortOption.trim());
        var foodList = converterService.convertFrom(filename.toLowerCase().trim());
        sorterService.sort(foodList, sortEnum);
        return foodList;
    }
}
