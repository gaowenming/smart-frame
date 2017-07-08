package com.smart.server.util;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author gaowenming
 */
@Getter
@Setter
@ToString
public class BaseJsonResult<T> implements Serializable {

    private static final long serialVersionUID = -6987458106094362943L;

    private Integer status = 200;//

    private String msg = "SUCCESS";//

    private T data = null;//

}
