package com.tallercarpro.appTaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.tallercarpro.appTaller.models.domain")
@EnableJpaRepositories("com.tallercarpro.appTaller.repository")
public class AppTallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppTallerApplication.class, args);
	}
}