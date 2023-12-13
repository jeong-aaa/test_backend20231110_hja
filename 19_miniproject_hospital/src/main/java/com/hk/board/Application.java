package com.hk.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hk.board")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
