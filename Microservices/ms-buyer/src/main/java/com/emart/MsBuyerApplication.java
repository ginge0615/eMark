package com.emart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class MsBuyerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBuyerApplication.class, args);
	}

}
