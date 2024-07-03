package com.netease.cloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author chenjiahan | chenjiahan@corp.netease.com | 2019/1/11
 **/
@SpringBootApplication
@EnableEurekaServer
public class NsfEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NsfEurekaApplication.class, args);
    }
}
