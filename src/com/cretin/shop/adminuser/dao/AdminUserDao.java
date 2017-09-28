package com.cretin.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.adminuser.vo.AdminUser;
import com.cretin.shop.user.vo.User;

/**
 * 后台登录的Dao层
 * 
 * @author cretin
 *
 */
public class AdminUserDao extends HibernateDaoSupport {

	/**
	 * 后台用户登录Dao层
	 * 
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> userList = getHibernateTemplate().find(hql,
				adminUser.getUsername(), adminUser.getPassword());
		if (userList != null && userList.size() > 0)
			return userList.get(0);
		else {
			return null;
		}
	}

}
