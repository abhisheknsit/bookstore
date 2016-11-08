package com.bookstore.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bookstore")
@EnableJpaRepositories("com.bookstore.repositories")
@ImportResource("classpath:/spring-datasource.xml")
public class AppConfiguration 
{ 
}
