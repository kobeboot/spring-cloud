package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2WebApplication.class, args);
    }

}
