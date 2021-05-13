package com.converter.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@XmlRootElement(name = "foods")
public class Food {

    private String name;
    private String price;
    private String description;
    private double calories;
}
