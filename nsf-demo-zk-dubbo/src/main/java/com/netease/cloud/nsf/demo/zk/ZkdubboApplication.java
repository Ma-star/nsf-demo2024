package com.netease.cloud.nsf.demo.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author chenjiahan
 */
@SpringBootApplication
public class ZkdubboApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(ZkdubboApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ZkdubboApplication.class);
	}
}
