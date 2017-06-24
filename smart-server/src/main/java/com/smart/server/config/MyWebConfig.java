package com.smart.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.smart.server.interceptor.TimeHandlerInterceptor;
import com.smart.server.interceptor.TokenHandlerInterceptor;

/**
 * 
 * ClassName: MyWebConfig <br/>
 * Function: 拦截器. <br/>
 * date: 2017年3月23日 下午8:57:44 <br/>
 *
 * @author gaowenming
 * @version
 * @since JDK 1.8
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 注册 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeHandlerInterceptor());
		// 针对需要校验的请求
		//registry.addInterceptor(new TokenHandlerInterceptor()).addPathPatterns("/api/xxxx", "/api/xxxxxxx");
	}

}