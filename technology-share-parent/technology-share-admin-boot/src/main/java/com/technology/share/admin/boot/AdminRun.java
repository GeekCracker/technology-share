package com.technology.share.admin.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.technology.share"})
@EnableAutoConfiguration
public class AdminRun {

    public static void main(String[] args) {
        SpringApplication.run(AdminRun.class,args);
    }
}
