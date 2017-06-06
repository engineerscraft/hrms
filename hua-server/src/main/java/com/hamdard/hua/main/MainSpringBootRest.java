package com.hamdard.hua.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.hamdard.hua.javaconfig.RestConfig;

@SpringBootApplication
@ComponentScan({ "com.hamdard.hua" })
@Import({ RestConfig.class })
public class MainSpringBootRest {
    
    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootRest.class, args);
    }
}