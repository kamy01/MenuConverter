package com.converter.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "breakfast_menu")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Menu {
    private List<Food> food;
}
