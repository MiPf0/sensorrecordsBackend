package com.lismic.loadbalancingtester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoadbalancingTesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadbalancingTesterApplication.class, args);
    }

}