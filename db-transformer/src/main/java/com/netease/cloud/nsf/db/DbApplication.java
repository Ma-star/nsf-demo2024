package com.netease.cloud.nsf.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DbApplication {
    @Autowired
    private DbService dbService;


    @PostConstruct
    public void doTask() {
        dbService.transformer();
    }

    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }
}
