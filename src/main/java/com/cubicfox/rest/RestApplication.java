package com.cubicfox.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "com.cubicfox")
@EntityScan(basePackages = "com.cubicfox.entity")
@EnableJpaRepositories(basePackages = "com.cubicfox.repository")
public class RestApplication {

	public static void main(String[] args)
	{
		System.out.println("----------------------SpringApplication.run(RestApplication.class, args);");
		SpringApplication.run(RestApplication.class, args);
	}

}
