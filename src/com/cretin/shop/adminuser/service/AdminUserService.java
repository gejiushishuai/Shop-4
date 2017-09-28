package com.cretin.shop.adminuser.service;

import com.cretin.shop.adminuser.dao.AdminUserDao;
import com.cretin.shop.adminuser.vo.AdminUser;
import com.cretin.shop.user.vo.User;

/**
 * 后台登录的业务层管理类
 * 
 * @author cretin
 *
 */
public class AdminUserService {
	// 注入AdminUserDao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 业务层后台用户登录
	 * 
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
}
