package com.liveeeasy.web.absorption.demo;

import com.liveeeasy.web.absorption.demo.spider.wuba.WuBaSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
    public static void main(String[] args) throws CloneNotSupportedException, IllegalAccessException {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        runWuBaSpider(run);
    }


    public static void runWuBaSpider(ApplicationContext applicationContext) throws IllegalAccessException {
        WuBaSpider wuBaSpider = applicationContext.getBean(WuBaSpider.class);
        wuBaSpider.startWuBa();
    }
}
