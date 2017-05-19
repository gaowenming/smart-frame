package com.smart.mapper;

import java.util.List;

import com.smart.mapper.base.IBaseMapper;
import com.smart.model.Dic;



/**
 * 
 * @author gaowenming
 *
 */
public interface DicMapper extends IBaseMapper<Dic> {
	public List<Dic> selectByname() throws Exception;

}
