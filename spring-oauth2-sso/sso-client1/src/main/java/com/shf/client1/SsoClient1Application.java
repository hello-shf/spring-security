package com.shf.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SsoClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(SsoClient1Application.class, args);
    }

}
