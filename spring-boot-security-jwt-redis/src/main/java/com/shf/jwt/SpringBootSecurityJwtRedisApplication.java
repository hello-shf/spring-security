package com.shf.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootSecurityJwtRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityJwtRedisApplication.class, args);
    }

}
