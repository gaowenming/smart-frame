package com.smart.mapper.base;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseMapper<T> {
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
	 * 批量删除
	 * 
	 * @param ids
	 * 
	 */
	public void deleteBatch(Serializable... ids) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<T> findPageList(Map<String, Object> map) throws Exception;

	/**
	 * 查询总数
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int count(Map<String, Object> map) throws Exception;
}
