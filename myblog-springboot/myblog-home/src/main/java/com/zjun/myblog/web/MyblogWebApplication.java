package com.zjun.myblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zjun.myblog.*")
public class MyblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyblogWebApplication.class, args);
    }
}
