package com.smart.service.base;

/**
 * 业务异常类
 *
 * ClassName:BusinessException <br/>
 * Date: 2017年5月13日 上午11:13:05 <br/>
 *
 * @author gaowenming
 * @see
 * @since JDK 1.6
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMsg;

    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorMsg);
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

    public BusinessException(String message) {
        super(message);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
