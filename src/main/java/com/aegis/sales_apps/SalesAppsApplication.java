package com.aegis.sales_apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class SalesAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesAppsApplication.class, args);
	}

}
