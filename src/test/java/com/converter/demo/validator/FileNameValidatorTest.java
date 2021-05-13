package com.converter.demo.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class FileNameValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "menu2.xml",
            ".json",
            "menux",
            "menux.jso",
            "menu.jso",
            "menux.xml"

    })
    void givenWrongFileNameThenExceptionIsThrown(String fileName) {
        assertThatThrownBy(() -> FileNameValidator.validateFileName(fileName))
                .hasMessage("Unsupported file name. Expecting menu.json/menu.xml");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "menu.xml",
            "menu.json",
            " menu.json ",
            "  menu.xml  "
    })
    void givenCorrectFileNameThenNoExceptionIsThrown() {
        assertDoesNotThrow(() -> FileNameValidator.validateFileName("menu.xml"));
    }
}
