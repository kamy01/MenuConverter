package com.converter.demo.file;

import java.io.InputStream;

public class InputStreamSelector {

    public static InputStream getInputStreamFrom(String fileName) {
        try {
            return InputStreamSelector.class.getResourceAsStream("/" + fileName.trim());
        } catch (Exception e) {
            throw new RuntimeException("Couldn't read data from " + fileName.trim());
        }
    }
}
