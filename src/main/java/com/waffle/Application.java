package com.waffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableFeignClients
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
