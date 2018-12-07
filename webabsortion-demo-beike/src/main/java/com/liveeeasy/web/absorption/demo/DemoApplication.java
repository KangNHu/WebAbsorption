package com.liveeeasy.web.absorption.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {



    /**
     *
     * @param args
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(DemoApplication.class, args);
    }

}
