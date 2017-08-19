package com.smart.server.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: TimeHandlerInterceptor <br/> Function: 方法执行时间拦截器. <br/> date: 2017年3月23日 下午8:46:58
 * <br/>
 *
 * author gaowenming since JDK 1.8
 */
@Slf4j
public class TimeHandlerInterceptor implements HandlerInterceptor {

    // 当前时间戳
    private ThreadLocal<Long> threadLocalTime = new ThreadLocal<>();

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
        log.info("[{}.{}] 执行耗时:{}ms", method.getDeclaringClass().getName(), method.getName(), executeTime);
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}