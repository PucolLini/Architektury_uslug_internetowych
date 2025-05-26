package com.example.OfficerService.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
	private final String officerServiceURL;
	private final String departmentServiceURL;

	public GatewayApplication(
		@Value("${game.officer-service.url}") String officerURL,
		@Value("${game.department-service.url}") String departmentURL
	){
		this.officerServiceURL = officerURL;
		this.departmentServiceURL = departmentURL;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator router(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("departments", route -> route
				.path("/api/departments", "/api/departments/{uuid}")
				.uri(departmentServiceURL)
			)
			.route("officers", route -> route
				.path("/api/officers", "/api/officers/{uuid}", "/api/departments/{uuid}/officers")
				.uri(officerServiceURL)
			)
			.build();
	}
}
