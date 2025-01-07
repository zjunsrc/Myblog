package com.zjun.myblog.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.zjun.myblog.*"})
public class MyblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyblogWebApplication.class, args);
    }
}
