package com.smart.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * ClassName:SystemConfig <br/>
 * Function: TODO 读取系统配置文件. <br/>
 * Date: 2017年3月23日 下午3:21:12 <br/>
 * 使用的时候： @Value("${name}") public String name;
 * 
 * @author gaowenming
 * @version
 * @since JDK 1.6
 * @see
 */
@Configuration
public class PropertiesConfig {
	/**
	 * 
	 * (这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author gaowenming
	 * @return
	 * @since JDK 1.8
	 */

	@Bean
	public PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
		ClassPathResource resource = new ClassPathResource("config.properties");
		PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocation(resource);
		return propertyPlaceholderConfigurer;
	}
}
