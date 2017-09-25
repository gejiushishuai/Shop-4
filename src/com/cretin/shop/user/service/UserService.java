package com.cretin.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.cretin.shop.user.dao.UserDao;
import com.cretin.shop.user.vo.User;
import com.cretin.shop.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * 
 * @author Cretin
 *
 */
@Transactional
public class UserService {
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 按用户名查询用户
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * 业务层完成用户注册
	 * 
	 * @param user
	 */
	public void save(User user) {
		//将数据存储到数据库
		user.setState(0);// 0 代表用户未激活 1 代表用户已经激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
	}

	/**
	 * 业务层用户登录
	 * @param user
	 */
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
}
