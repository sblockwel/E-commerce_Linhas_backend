package com.lb.ecommerce;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Linhas Blumenau API", version = "1.0", description = "E-commerce de linhas"))
@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
