package com.csm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoffeeShopManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopManagementApplication.class, args);
	}

}
