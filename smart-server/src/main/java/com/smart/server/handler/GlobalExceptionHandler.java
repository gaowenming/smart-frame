package com.smart.server.handler;

import com.smart.server.util.BaseJsonResult;
import com.smart.service.base.BusinessException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理 ClassName: GlobalExceptionHandler <br/>
 * date: 2017年4月28日 下午10:14:26 <br/>
 *
 * author gaowenming
 * version
 * since JDK 1.8
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 业务异常
	 *
	 * @author gaowenming
	 * @param req
	 * @param e
	 * return
	 * throws Exception
	 * since JDK 1.8
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BaseJsonResult<Object> defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
		log.error("<-----------------系统响应异常----------------->", e);
		BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
		baseJsonResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		baseJsonResult.setMsg("请求失败,请稍后重试！");
		return baseJsonResult;
	}

	/**
	 * 业务异常
	 *
	 * @author gaowenming
	 * @param req
	 * @param e
	 * return
	 * throws Exception
	 * since JDK 1.8
	 */
	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public BaseJsonResult<Object> smartBusinessExceptionHandler(HttpServletRequest req, BusinessException e) throws Exception {
		log.error("<-----------------系统响应异常----------------->", e);
		BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
		baseJsonResult.setStatus(e.getErrorCode());
		baseJsonResult.setMsg(e.getErrorMsg());
		return baseJsonResult;
	}
}