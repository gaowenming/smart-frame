package com.smart.service.base;

/**
 * 业务异常类
 *
 * ClassName:BusinessException <br/> Date: 2017年5月13日 上午11:13:05 <br/>
 *
 * @author gaowenming
 * @see
 * @since JDK 1.6
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private BusinessErrorMsg businessErrorMsg = BusinessErrorMsg.SYSTEM_ERROR;

    public BusinessException(BusinessErrorMsg businessErrorMsg) {
        super();
        this.businessErrorMsg = businessErrorMsg;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(BusinessErrorMsg businessErrorMsg, Throwable cause) {
        super();
        this.businessErrorMsg = businessErrorMsg;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
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

    public BusinessErrorMsg getBusinessErrorMsg() {
        return businessErrorMsg;
    }

    public void setBusinessErrorMsg(BusinessErrorMsg businessErrorMsg) {
        this.businessErrorMsg = businessErrorMsg;
    }
}
