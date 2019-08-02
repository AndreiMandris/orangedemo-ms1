package com.orangedemo.ms1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Ms1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ms1Application.class, args);
    }

}
