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

	/**
	 * Dao层保存一级分类的方法
	 * @param category
	 */
	public void save(Category category) {
		getHibernateTemplate().save(category);
	}

	/**
	 * 根据id查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * dao层删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		getHibernateTemplate().delete(category);
	}

	/**
	 * dao层修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		getHibernateTemplate().update(category);
	}

}
