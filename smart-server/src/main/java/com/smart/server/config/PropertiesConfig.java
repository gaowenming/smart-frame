package com.smart.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * 读取系统配置文件. <br/>
 * Date: 2017年3月23日 下午3:21:12 <br/>
 * 使用的时候： @Value("${name}") public String name;
 * 
 * @author gaowenming
 * @since JDK 1.6
 */
@Configuration
public class PropertiesConfig {
	/**
	 * author gaowenming
	 * since JDK 1.8
	 */
	@Bean
	public PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
		ClassPathResource resource = new ClassPathResource("config.properties");
		PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocation(resource);
		return propertyPlaceholderConfigurer;
	}
}
