package com.example.SpingOnlineSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example")
public class SpingOnlineSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingOnlineSiteApplication.class, args);
	}

}
