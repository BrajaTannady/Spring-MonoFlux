package com.example.SpringMonoFlux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringMonoFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMonoFluxApplication.class, args);
	}

}
