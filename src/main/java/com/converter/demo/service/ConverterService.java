package com.converter.demo.service;


import com.converter.demo.converter.FoodConverter;
import com.converter.demo.converter.FoodConverterFactory;
import com.converter.demo.model.Food;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.converter.demo.validator.FileNameValidator.validateFileName;


@Service
public class ConverterService {

    public List<Food> convertFrom(String fileName) {
        validateFileName(fileName);
        var fileExtension = FilenameUtils.getExtension(fileName);
        FoodConverter foodConverter = FoodConverterFactory.getConverter(fileExtension);
        return foodConverter.convert(fileName);
    }
}
