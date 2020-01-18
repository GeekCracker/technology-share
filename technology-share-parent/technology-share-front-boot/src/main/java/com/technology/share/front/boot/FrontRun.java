package com.technology.share.front.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.technology.share"})
@MapperScan(basePackages = {"com.technology.share.mapper"})
@EnableAutoConfiguration
public class FrontRun {
    public static void main(String[] args) {
        SpringApplication.run(FrontRun.class,args);
    }
}
