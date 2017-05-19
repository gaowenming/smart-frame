package com.smart.server.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * ClassName: TimeHandlerInterceptor <br/>
 * Function: 方法执行时间拦截器. <br/>
 * date: 2017年3月23日 下午8:46:58 <br/>
 *
 * @author gaowenming
 * @version
 * @since JDK 1.8
 */
public class TimeHandlerInterceptor implements HandlerInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

	// 当前时间戳
	private ThreadLocal<Long> threadLocalTime = new ThreadLocal<Long>();

	/**
	 * controller 执行之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		threadLocalTime.set(startTime);
		return true;
	}

	/**
	 * controller 执行之后，且页面渲染之前调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		long endTime = System.currentTimeMillis();
		long startTime = threadLocalTime.get();
		long executeTime = endTime - startTime;
		logger.info("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : " + executeTime + "ms");
	}

	/**
	 * 页面渲染之后调用，一般用于资源清理操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}