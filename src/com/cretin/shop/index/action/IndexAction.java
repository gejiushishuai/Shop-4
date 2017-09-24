package com.cretin.shop.index.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author Cretin
 *
 */
public class IndexAction extends ActionSupport {
	/**
	 * 执行访问首页的方法
	 */
	public String execute(){
		return "index";
	}
}
