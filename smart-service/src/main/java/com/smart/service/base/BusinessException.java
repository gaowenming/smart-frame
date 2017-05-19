package com.smart.service.base;

/**
 * 业务异常类
 * 
 * ClassName:BusinessException <br/>
 * Date: 2017年5月13日 上午11:13:05 <br/>
 * 
 * @author gaowenming
 * @version
 * @since JDK 1.6
 * @see
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMsg;
	private Integer errorCode;

	public BusinessException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public BusinessException(String errorMsg, Integer errorCode) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(Integer errorCode, String errorMsg, Throwable cause) {
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
