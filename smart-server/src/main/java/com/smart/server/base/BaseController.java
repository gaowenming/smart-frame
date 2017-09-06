package com.smart.server.base;


/**
 * @author gaowenming
 * @create 2017-09-06 22:17
 * @desc Base
 **/
public class BaseController {

    /**
     * 成功返回
     *
     * 指定返回数据
     */
    protected <T> BaseJsonResult<T> successResult(T result) throws Exception {
        BaseJsonResult<T> baseJsonResult = new BaseJsonResult<>();
        baseJsonResult.setData(result);
        return baseJsonResult;
    }

    /**
     * 成功返回
     *
     * 只返回状态和消息，内容为空
     */
    protected BaseJsonResult successNullDataResult() throws Exception {
        BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
        baseJsonResult.setData(null);
        return baseJsonResult;
    }
}
