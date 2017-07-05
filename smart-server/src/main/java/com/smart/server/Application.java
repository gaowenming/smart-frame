package com.smart.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启事务
@ComponentScan("com.smart") // 扫描service和controller
@MapperScan("com.smart.mapper") // 扫描mapper
@EnableAspectJAutoProxy//允许动态代理
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}