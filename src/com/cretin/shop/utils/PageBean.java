package com.cretin.shop.utils;

import java.util.List;

/**
 * 分页类的封装
 * 
 * @author cretin
 *
 */
public class PageBean<T> {
	private int page;
	private int totalCount;
	private int totalPage;
	private int limit;
	private List<T> list;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", limit=" + limit + "]";
	}
}
