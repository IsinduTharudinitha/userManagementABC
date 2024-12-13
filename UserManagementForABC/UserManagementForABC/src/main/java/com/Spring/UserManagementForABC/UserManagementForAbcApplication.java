package com.Spring.UserManagementForABC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.Spring.UserManagementForABC.Repository")
public class UserManagementForAbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementForAbcApplication.class, args);
	}

}
