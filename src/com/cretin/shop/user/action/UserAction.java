package com.cretin.shop.user.action;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.user.service.UserService;
import com.cretin.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

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
		// 获得Response对象向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (existUser != null) {
			// 查询到了用户 用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没有查到 用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册的方法
	 * 
	 * @return
	 */
	public String regist() {
		// 判断验证码是否正确
		String sessionCheckCode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(sessionCheckCode)) {
			// 验证码正确 可以提交
			addActionError("验证码输入错误");
			return "checkcodeFail";
		} 

		userService.save(user);
		addActionMessage("注册成功，请去登录！");
		return "msg";
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// 登录失败
			addActionError("登录失败，用户名或密码错误");
			return LOGIN;
		} else {
			// 登录成功
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	/**
	 * 用户退出登录
	 * 
	 * @return
	 */
	public String quit() {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

	@Override
	public User getModel() {
		return user;
	}
}
