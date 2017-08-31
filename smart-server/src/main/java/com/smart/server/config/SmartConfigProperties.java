package com.smart.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * 业务配置
 * Author: gaowenming
 * Description:
 * Date: Created in 11:59 2017/7/15.
 */
@Configuration
@PropertySource("classpath:config.properties")//注意路径
@ConfigurationProperties(prefix = "smart")
@Data
public class SmartConfigProperties {
    private String username;
    private String secretKey;
}
