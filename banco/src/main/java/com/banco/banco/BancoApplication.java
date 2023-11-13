package com.banco.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);

		System.out.println("hello world");
	}

}
