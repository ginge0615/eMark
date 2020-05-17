package com.emart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class MsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBootApplication.class, args);
	}

}
