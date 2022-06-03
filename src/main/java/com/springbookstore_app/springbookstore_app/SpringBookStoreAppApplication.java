package com.springbookstore_app.springbookstore_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringBookStoreAppApplication {

    public static void main(String[] args) {
        ApplicationContext context=  SpringApplication.run(SpringBookStoreAppApplication.class, args);
        log.info("BookStore app Started in {} Environment",
                context.getEnvironment().getProperty("environment"));
        log.info("BookStore DB User is {} ",
                context.getEnvironment().getProperty("spring.datasource.username"));
        System.out.println("Welcome to  Book Store !!");
    }

}
