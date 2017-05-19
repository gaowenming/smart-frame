package com.smart.server.util;

import java.io.Serializable;

/**
 * 
 * @author gaowenming
 *
 */
public class BaseJsonResult<T> implements Serializable {

	private static final long serialVersionUID = -6987458106094362943L;

	private int status = 200;//

	private String msg = "SUCCESS";//

	private T data = null;//

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
