package com.smart.server.interceptor;

import com.smart.service.base.BusinessException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * ClassName: TokenHandlerInterceptor <br/>
 * Function: token拦截器. <br/>
 * date: 2017年3月23日 下午8:46:58 <br/>
 *
 * @author gaowenming
 * @version
 * @since JDK 1.8
 */
@Slf4j
public class TokenHandlerInterceptor implements HandlerInterceptor {

	/**
	 * token放在header中
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		String token = request.getHeader("token");
		log.info("TokenHandlerInterceptor----- " + url + ", token=" + token);
		if (StringUtils.isEmpty(token)) {
			throw new BusinessException("Token is Null");
		}

		//校验token是否过期和正确
		//TODO
		
		return true;
	}

	/**
	 * controller 执行之后，且页面渲染之前调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 页面渲染之后调用，一般用于资源清理操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}