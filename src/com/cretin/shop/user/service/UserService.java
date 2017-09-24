package com.cretin.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.cretin.shop.user.dao.UserDao;
import com.cretin.shop.user.vo.User;

/**
 * 用户模块业务层代码
 * @author Cretin
 *
 */
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//按用户名查询用户
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
}
