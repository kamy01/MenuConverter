package com.converter.demo.validator;

public class FileNameValidator {
    public static void validateFileName(String filename) {
        if (!filename.matches("menu\\.(json|xml)$")) {
            throw new RuntimeException("Unsupported file name. Expecting menu.json/menu.xml");
        }
    }
}
