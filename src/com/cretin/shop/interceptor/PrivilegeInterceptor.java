package com.cretin.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限校验拦截器 判断登录之后的用户才能进行访问
 * 
 * @author cretin
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// 判断Session是否保存了用户信息
		AdminUser existAdminUser = (AdminUser) ServletActionContext
				.getRequest().getSession().getAttribute("existAdminUser");
		if (existAdminUser == null) {
			// 未登录
			ActionSupport actionSupport = (ActionSupport) arg0.getAction();
			actionSupport.addActionError("您是非法用户，请先登录");
			return "loginFail";
		} else {
			// 合法用户
			return arg0.invoke();
		}
	}

}
