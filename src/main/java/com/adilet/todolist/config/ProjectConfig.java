package com.adilet.todolist.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.adilet.todolist.repository")
public class ProjectConfig {
}
