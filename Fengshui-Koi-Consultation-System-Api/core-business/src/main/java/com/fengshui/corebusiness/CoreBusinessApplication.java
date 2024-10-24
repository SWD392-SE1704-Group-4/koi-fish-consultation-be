package com.fengshui.corebusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EntityScan(basePackages = {"com.fengshui.common.repository.postgresql.entities"})
@EnableJpaRepositories(basePackages = {"com.fengshui.common.repository"})
public class CoreBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreBusinessApplication.class, args);
    }
}
