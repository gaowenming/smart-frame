package com.smart.service.impl;import java.io.Serializable;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import org.springframework.util.StringUtils;import com.smart.mapper.DicMapper;import com.smart.model.Dic;import com.smart.service.IDicService;import com.smart.service.base.page.Page;import com.smart.service.base.page.Sorter;/** * <p> * </p> * generate time:2014-9-17 16:49:49 *  */@Service(value = "dicService")public class DicServiceImpl implements IDicService {	@Autowired	private DicMapper dicMapper;	@Transactional	@Override	public void save(Dic model) throws Exception {		dicMapper.save(model);	}	@Transactional	@Override	public void update(Dic model) throws Exception {		dicMapper.update(model);	}	@Transactional	@Override	public void delete(Serializable id) throws Exception {		dicMapper.delete(id);	}	@Override	public Dic get(Serializable id) throws Exception {		return dicMapper.get(id);	}	@Override	public List<Dic> findAllList() throws Exception {		return dicMapper.findAllList();	}	@Override	public List<Dic> findByPage(Dic model, Sorter sorter, Page page) throws Exception {		Map<String, Object> map = new HashMap<String, Object>();		if (sorter == null || StringUtils.isEmpty(sorter.getSortName())) {			sorter = new Sorter();			sorter.setSortName("id");			sorter.setSortType("DESC");		}		page.setPageSize(20);		map.put("name", model.getName());		map.put("start", page.getRecordStartIndex());		map.put("pageSize", page.getPageSize());		map.put("sortField", sorter.getSortName());		map.put("sortType", sorter.getSortType());		int count = dicMapper.count(map);		page.setTotal(count);		return dicMapper.findPageList(map);	}	@Transactional	@Override	public void deleteBatch(Serializable... ids) throws Exception {		dicMapper.deleteBatch(ids);	}}