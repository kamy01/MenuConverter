package com.converter.demo.validator;

import com.converter.demo.model.enums.SortOption;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SortOrderValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "as",
            " cx",
            "",
            "ascc",
            "Dsc",
            "ddsc "

    })
    void givenWrongFileNameThenExceptionIsThrown(String sortOrder) {
        assertThatThrownBy(() -> SortOrderValidator.validateAndGetSortOption(sortOrder))
                .hasMessage("Invalid sort option. Expecting ASC/DESC");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "asc",
            "ASC",
            "aSc",
            "AsC",
            "desc",
            "AsC",
            "DeSc",
            "DESC",
            " DeSc  ",
            "ASC   "
    })
    void givenCorrectFileNameThenNoExceptionIsThrown(String sortOrder) {
     assertThat(SortOrderValidator.validateAndGetSortOption(sortOrder))
             .isIn(SortOption.values());
    }

}
