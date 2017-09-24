package com.cretin.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.user.vo.User;

/**
 * 用户模块持久层代码
 * @author Cretin
 *
 */
public class UserDao extends HibernateDaoSupport{
	//按名称查询是否有该用户
	public User findByUsername(String username){
		String hql = "from User where username=?";
		List<User> userList = getHibernateTemplate().find(hql,username);
		if(userList!=null&&userList.size()>0){
			return userList.get(0);
		}else{
			return null;
		}
	}
}
