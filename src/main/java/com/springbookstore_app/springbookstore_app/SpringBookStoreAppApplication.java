package com.springbookstore_app.springbookstore_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringBookStoreAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBookStoreAppApplication.class, args);
        System.out.println("Welcome to  Book Store !!");
    }

}
