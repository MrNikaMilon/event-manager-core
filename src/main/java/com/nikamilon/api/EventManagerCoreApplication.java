package com.nikamilon.api;

import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.model.dictionary.UserRole;
import com.nikamilon.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication()
public class EventManagerCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventManagerCoreApplication.class, args);
	}
}
