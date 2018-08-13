package com.waffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class WaffleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaffleApplication.class, args);
	}

}
