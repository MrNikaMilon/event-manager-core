package com.nikamilon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication()
public class EventManagerCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventManagerCoreApplication.class, args);
	}
}
