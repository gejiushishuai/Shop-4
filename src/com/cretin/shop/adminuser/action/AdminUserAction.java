package com.cretin.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.adminuser.service.AdminUserService;
import com.cretin.shop.adminuser.vo.AdminUser;
import com.cretin.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台用户管理的Action
 * 
 * @author cretin
 *
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	// 模型驱动的对象
	private AdminUser adminUser = new AdminUser();
	// 注入Service
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public AdminUser getModel() {
		return adminUser;
	}

	/**
	 * 后台登录的方法
	 */
	public String login() {
		// 登录
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			// 登录失败
			addActionError("登录失败，用户名或密码错误");
			return "loginFail";
		} else {
			// 登录成功
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}

	/**
	 * 后台登录的方法
	 */
	public String loginSuccess() {
		return "loginSuccess";
	}
}
