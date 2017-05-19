package com.smart.service.base.page;
/**
 * Description: <分页信息线程安全持有类>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014-1-13 上午10:11:30
 * 
 * @author gaowenming
 * @version V1.0
 */
public class PageHolder {
	private static ThreadLocal<Page> locale = new ThreadLocal<Page>() {
		protected Page initialValue() {
			return new Page(0, 15, 0);
		}
	};

	public static Page getPage() {
		return locale.get();
	}

	public static void setPage(Page target) {
		locale.set(target);
	}
}
