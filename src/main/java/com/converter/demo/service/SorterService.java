package com.converter.demo.service;

import com.converter.demo.model.Food;
import com.converter.demo.model.enums.SortOption;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SorterService {


    public void sort(List<Food> foodList, SortOption sortOrder) {
        switch (sortOrder) {
            case DESC:
                foodList.sort(Comparator.comparing(Food::getName, String.CASE_INSENSITIVE_ORDER).reversed());
                break;
            case ASC:
                foodList.sort(Comparator.comparing(Food::getName, String.CASE_INSENSITIVE_ORDER));
        }
    }
}
