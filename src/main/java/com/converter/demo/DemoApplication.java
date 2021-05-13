package com.converter.demo;

import com.converter.demo.controller.MenuController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
    private MenuController menuController;

    @Autowired
    public DemoApplication(MenuController menuController) {
        this.menuController = menuController;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length > 1) {
            try {
                var foodList = menuController.convertAndSort(args[0], args[1]);
                foodList.forEach(System.out::println);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            System.out.println("Not enough arguments, example run : java –jar menu.jar menu.json desc");
        }
    }

}
