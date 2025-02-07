package com.jeriam.demonstracao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Documentação Swagger")})
public class DemonstracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemonstracaoApplication.class, args);
	}

}
