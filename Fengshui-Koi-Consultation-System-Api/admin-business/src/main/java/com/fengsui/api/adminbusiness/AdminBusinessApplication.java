package com.fengsui.api.adminbusiness;

import com.fengshui.common.repository.postgresql.entities.AppUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@SpringBootApplication
@EntityScan(basePackages = {"com.fengshui.common.repository.postgresql.entities"})
@EnableJpaRepositories(basePackages = {"com.fengshui.common.repository.postgresql.entities"})
public class AdminBusinessApplication {
	public static void main(String[] args) {
//		AppUser appuser = new AppUser(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), "asdasd");
//		System.out.println(appuser.getId());
		SpringApplication.run(AdminBusinessApplication.class, args);
	}
}
