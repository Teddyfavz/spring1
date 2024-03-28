package com.favcode.favschool;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.favcode.favschool.repository")
@EntityScan("com.favcode.favschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class FavschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavschoolApplication.class, args);
	}

}
