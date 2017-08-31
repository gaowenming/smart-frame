package com.smart.server.config;

import com.smart.server.interceptor.TimeHandlerInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ClassName: SmartWebConfig <br/> Function: 拦截器. <br/> date: 2017年3月23日 下午8:57:44 <br/>
 *
 * @author gaowenming
 * @since JDK 1.8
 */
@Configuration
public class SmartWebConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeHandlerInterceptor());
    }

}