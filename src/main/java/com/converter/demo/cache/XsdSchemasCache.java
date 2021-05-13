package com.converter.demo.cache;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.converter.demo.file.InputStreamSelector.getInputStreamFrom;

@Slf4j
public class XsdSchemasCache {

    private static final Map<String, byte[]> test = new HashMap<>();

    static {
        try {
            test.put("menu.xml", getInputStreamFrom("menu.xsd").readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream getXsdFor(String fileName) {
        var xsdSchema = test.get(fileName);
        if (xsdSchema == null) {
            throw new RuntimeException("Unknown xsd schema for " + fileName);
        }
        return new ByteArrayInputStream(xsdSchema);
    }
}
