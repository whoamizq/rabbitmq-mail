package com.whoamizq.rabbitmqmail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.whoamizq.rabbitmqmail.mapper")
@EnableScheduling
public class RabbitmqMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqMailApplication.class, args);
    }

}
