package com.smart.server.handler;

import com.smart.server.util.BaseJsonResult;
import com.smart.service.base.BusinessErrorMsg;
import com.smart.service.base.BusinessException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 *
 * 2017年4月28日 下午10:14:26 <br/>
 *
 * author gaowenming version since JDK 1.8
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @author gaowenming param req param e return throws Exception since JDK 1.8
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseJsonResult<Object> defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("<-----------------系统响应异常----------------->", e);
        printMethodParameters(req);
        BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
        baseJsonResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        baseJsonResult.setMsg("请求失败,请稍后重试！");
        return baseJsonResult;
    }

    /**
     * 业务异常
     *
     * @author gaowenming param req param e return throws Exception since JDK 1.8
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public BaseJsonResult<Object> smartBusinessExceptionHandler(HttpServletRequest req, BusinessException e) throws Exception {
        BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
        BusinessErrorMsg businessErrorMsg = e.getBusinessErrorMsg();
        log.warn("catch BusinessException,code:{},message:{}", businessErrorMsg.getErrorCode(), businessErrorMsg.getErrorMessage());
        printMethodParameters(req);
        baseJsonResult.setStatus(businessErrorMsg.getErrorCode());
        baseJsonResult.setMsg(businessErrorMsg.getErrorMessage());
        return baseJsonResult;
    }

    /**
     * 记录方法的入参
     */
    private static void printMethodParameters(HttpServletRequest request) {
        StringBuilder methodInfo = new StringBuilder();
        methodInfo.append("url=").append(request.getServletPath());
        if (request.getQueryString() != null) {
            methodInfo.append(request.getQueryString()).append(" - ");
        } else {
            Map<String, String[]> parameters = request.getParameterMap();
            if (parameters.size() != 0) {
                methodInfo.append(" - [");
            }
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String message = "";
                if (value.getClass().isArray()) {
                    Object[] args = (Object[]) value;
                    message = " " + key + "=" + Arrays.toString(args) + " ";
                } else {
                    message = key + "=" + String.valueOf(value) + " ";
                }
                methodInfo.append(message);
            }
            if (parameters.size() != 0) {
                methodInfo.append("]");
            }
        }
        log.info(methodInfo.toString());
    }
}