package com.converter.demo.converter.impl;

import com.converter.demo.cache.XsdSchemasCache;
import com.converter.demo.converter.FoodConverter;
import com.converter.demo.model.Food;
import com.converter.demo.model.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.converter.demo.file.InputStreamSelector.getInputStreamFrom;


@Service
@Slf4j
public class XmlFoodConverter implements FoodConverter {

    @Override
    public String getType() {
        return "xml";
    }

    @Override
    public List<Food> convert(String fileName) {
        List<Food> foods = new ArrayList<>();

        var inputStream = getInputStreamFrom(fileName);
        var root = readRootFromInputStream(fileName, inputStream);

        Optional.ofNullable(root)
                .map(Menu::getFood)
                .ifPresent(foods::addAll);

        return foods;
    }

    private Menu readRootFromInputStream(String fileName, InputStream inputStream) {
        Menu root;
        try {
            Unmarshaller jaxbUnmarshaller = getUnmarshaller(fileName);
            root = (Menu) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException | SAXException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Error while converting from xml" + fileName, e);
        }
        return root;
    }

    private Unmarshaller getUnmarshaller(String filename) throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        jaxbUnmarshaller.setSchema(getSchema(filename));
        return jaxbUnmarshaller;
    }

    private Schema getSchema(String filename) throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return sf.newSchema(new StreamSource(XsdSchemasCache.getXsdFor(filename)));
    }
}
