package com.cretin.shop.product.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageHibernateCallback;

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
	public Product findByPid(Integer pid) {
		return getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * 根据一级分类的id查询商品的数量
	 * 
	 * @param cid
	 * @return
	 */
	public int getAllCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据分类的id查询商品的集合
	 * 
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> getProductsByPageCid(Integer cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid =
		// cs.cid and cs.csid = p.csid and c.cid = 1;
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据二级分类的id查询商品的数量
	 * 
	 * @param cid
	 * @return
	 */
	public int getAllCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据二级分类的id查询商品的集合
	 * 
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> getProductsByPageCsid(Integer csid, int begin,
			int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid =
		// cs.cid and cs.csid = p.csid and c.cid = 1;
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { csid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 获取所有商品的数量
	 * 
	 * @return
	 */
	public int getAllCount() {
		String hql = "select count(*) from Product";
		List<Long> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> getProductsByPage(int begin, int limit) {
		String hql = "from Product p order by p.pdate desc";
		List<Product> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null,
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
