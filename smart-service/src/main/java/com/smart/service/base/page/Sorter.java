package com.smart.service.base.page;

/**
 * Description: <排序类>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014-1-13 上午10:14:53
 * 
 * @author gaowenming
 * @version V1.0
 */
public class Sorter {
	public static final String DESC = "desc";

	public static final String ASC = "asc";

	/**
	 * @Fields sortName : TODO(排序的列名称)
	 */
	private String sortName;

	/**
	 * @Fields sortType : TODO(排序类型：升序、降序)
	 */
	private String sortType;

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
