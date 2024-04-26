package com.bootcamp2024.bootcamp2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Bootcamp2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Bootcamp2024Application.class, args);
	}

}
