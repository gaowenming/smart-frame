package com.smart.server.annotation;

import com.smart.service.base.BusinessException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaowenming on 2017/6/15.
 */
@Aspect
@Component
public class TokenValidationInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(TokenValidationInterceptor.class);

    @Pointcut("@annotation(com.smart.server.annotation.TokenValidation)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doTokenValidation(JoinPoint point) throws Throwable {
        HttpServletRequest request = null;

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        request = sra.getRequest();

        String url = request.getServletPath();
        String token = request.getHeader("token");
        logger.info("TokenHandlerInterceptor----- " + url + ", token=" + token);
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(0, "Token is Null");
        }

        //校验token是否过期和正确
        //TODO

    }

}
