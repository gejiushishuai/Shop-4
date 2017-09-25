package com.cretin.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.product.vo.Product;

/**
 * 商品的持久层代码
 * 
 * @author cretin
 *
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * 查询热门商品
	 * 
	 * @return
	 */
	public List<Product> getHotProducts() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门商品 is_hot == 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序排序
		criteria.addOrder(Order.desc("pdate"));
		return (List<Product>) getHibernateTemplate().findByCriteria(criteria,
				0, 10);
	}

	/**
	 * 查询最新的商品
	 * 
	 * @return
	 */
	public List<Product> getNewestProducts() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 倒序排序
		criteria.addOrder(Order.desc("pdate"));
		return (List<Product>) getHibernateTemplate().findByCriteria(criteria,
				0, 10);
	}

	/**
	 * 根据id查询商品
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(String pid) {
		return getHibernateTemplate().get(Product.class, pid);
	}
}
