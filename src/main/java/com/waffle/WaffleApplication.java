package com.waffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableFeignClients
public class WaffleApplication {

	public static void main(String[] args) {

		SpringApplication.run(WaffleApplication.class, args);
	}

}
