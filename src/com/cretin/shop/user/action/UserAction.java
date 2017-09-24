package com.cretin.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.user.service.UserService;
import com.cretin.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 用户模块的Action
 * 
 * @author Cretin
 * 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动需要使用的bean
	private User user = new User();
	// 注入UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转到注册页面的执行方法
	 */
	public String registPage() {
		return "registPage";
	}

	/**
	 * AJAX进行异步校验用户名的方法
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询
		User existUser = userService.findByUsername(user.getUsername());
		//获得Response对象向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			//查询到了用户 用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else{
			//没有查到 用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	@Override
	public User getModel() {
		return user;
	}
}
