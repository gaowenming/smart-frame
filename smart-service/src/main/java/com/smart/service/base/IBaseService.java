package com.smart.service.base;
import java.io.Serializable;
import java.util.List;

import com.smart.service.base.page.Page;
import com.smart.service.base.page.Sorter;

/**
 * 
 * 
 * @Description: TODO
 * @author gaowm
 * @date 2013-9-1 下午7:51:08
 * 
 */
public interface IBaseService<T> {

	/**
	 * 保存模型对象
	 * 
	 * @param model
	 * @return
	 */
	public void save(T model) throws Exception;

	/**
	 * 更新模型对象
	 * 
	 * @param model
	 * 
	 */
	public void update(T model) throws Exception;

	/**
	 * 删除模型对象
	 * 
	 * @param id
	 */
	public void delete(Serializable id) throws Exception;

	/**
	 * 根据主键获取模型对象
	 * 
	 * @param id
	 * @return
	 */
	public T get(Serializable id) throws Exception;

	/**
	 * 获取所有模型对象
	 * 
	 * @return
	 */
	public List<T> findAllList() throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param model
	 *            实体对象
	 * @param sorter
	 *            排序
	 * @param page
	 *            分页
	 * @return
	 */
	public List<T> findByPage(T model, Sorter sorter, Page page)
			throws Exception;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * 
	 */
	public void deleteBatch(Serializable... ids) throws Exception;
}
