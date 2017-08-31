package com.smart.service.base;

/**
 * 错误信息描述 gaowenming 2017-08-31
 */
public enum BusinessErrorMsg {
    SYSTEM_ERROR(9999, "系统错误"),
    VALIDATION_TOKEN_NULL(1000, "token is null"),
    VALIDATION_PARAM_ERROR(1001, "参数错误");

    BusinessErrorMsg(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private Integer errorCode = 9999;
    private String errorMessage = "系统错误";

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
