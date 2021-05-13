package com.converter.demo.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
public class FoodConverterFactoryTest {


    @ParameterizedTest
    @ValueSource(strings = {
            "json",
            "xml",
            "XmL",
            "jSon",
            "xmL"
    })
    void givenCorrectFileExtensionThenConverterIsFound(String type) {
        assertThat(FoodConverterFactory.getConverter(type)).isInstanceOf(FoodConverter.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "json2",
            "xmal",
            "XmxL",
            "",
            "fdsa"
    })
    void givenInCorrectFileExtensionThenConverterIsNotFound(String type) {
        assertThatThrownBy(() -> FoodConverterFactory.getConverter(type))
                .hasMessageContaining("Unknown food converter type:");
    }
}
