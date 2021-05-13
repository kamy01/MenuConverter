package com.converter.demo.validator;

import com.converter.demo.model.enums.SortOption;

public class SortOrderValidator {

    public static SortOption validateAndGetSortOption(String sortOption) {
        try {
            return SortOption.valueOf(sortOption.toUpperCase().trim());
        } catch (Exception e) {
            throw new RuntimeException("Invalid sort option. Expecting ASC/DESC");
        }
    }
}
