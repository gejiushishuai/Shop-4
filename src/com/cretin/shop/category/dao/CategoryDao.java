package com.cretin.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.category.vo.Category;

/**
 * 一级分类的持久层对象
 * 
 * @author cretin
 *
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * 查询所有一级分类
	 * 
	 * @return
	 */
	public List<Category> getAllCategory() {
		String hql = "from Category";
		return (List<Category>) getHibernateTemplate().find(hql);
	}

}
