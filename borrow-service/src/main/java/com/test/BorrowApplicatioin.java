package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BorrowApplicatioin {
    public static void main(String[] args) {
        SpringApplication.run(BorrowApplicatioin.class, args);
    }
}
