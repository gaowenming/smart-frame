package com.smart.model;

import java.io.Serializable;

/**
 * Description: <Dic>. <br>
 * 
 * generate time:2014-9-17 16:49:48
 * 
 */
public class Dic implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer id;

	private java.lang.String name;

	private java.lang.String dicValue;

	private java.lang.String remark;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDicValue() {
		return dicValue;
	}

	public void setDicValue(java.lang.String dicValue) {
		this.dicValue = dicValue;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

}
