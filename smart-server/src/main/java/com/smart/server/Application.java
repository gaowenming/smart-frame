package com.smart.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.smart") // 扫描service和controller
@MapperScan("com.smart.mapper") // 扫描mapper
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}