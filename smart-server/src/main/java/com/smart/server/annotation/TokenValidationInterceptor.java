package com.smart.server.annotation;

import com.smart.service.base.BusinessException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by gaowenming on 2017/6/15.
 */
@Aspect
@Component
@Slf4j
public class TokenValidationInterceptor {

    //注解在类上面@within
    @Pointcut("@within(com.smart.server.annotation.TokenValidation)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void tokenValidationType(JoinPoint point) throws Throwable {
        commonTokenValidation(point);
    }


    //注解在方法上面@annotation
    @Pointcut("@annotation(com.smart.server.annotation.TokenValidation)")
    public void pointcutMethod() {
    }

    @Before("pointcutMethod()")
    public void tokenValidationMethod(JoinPoint point) throws Throwable {
        commonTokenValidation(point);
    }


    //公共的校验
    public static void commonTokenValidation(JoinPoint point) {
        HttpServletRequest request ;

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        request = sra.getRequest();

        String url = request.getServletPath();
        String token = request.getHeader("token");
        log.info("TokenHandlerInterceptor----- url:{},token:{}  ",url, token);
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(0, "Token is Null");
        }

        //校验token是否过期和正确
        //TODO
    }

}
