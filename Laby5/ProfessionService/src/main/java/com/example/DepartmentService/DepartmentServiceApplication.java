package com.example.DepartmentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	@Bean
	@Qualifier("officer-service")
	public RestTemplate departmentServiceRestTemplate(
		@Value("${game.officer-service.url}") String departmentServiceUrl
	) {
		return new RestTemplateBuilder()
				.rootUri(departmentServiceUrl)
				.build();
	}

}
