package com.cretin.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.order.vo.Order;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageHibernateCallback;

/**
 * 订单模块Dao层
 * 
 * @author cretin
 *
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * Dao层保存订单
	 * 
	 * @param order
	 */
	public void save(Order order) {
		getHibernateTemplate().save(order);
	}

	/**
	 * Dao层我的订单个数统计
	 * 
	 * @param uid
	 * @return
	 */
	public Integer findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * Dao层我的订单查询
	 * 
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Order> findOrdersByPageUid(Integer uid, Integer begin,
			Integer limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid =
		// cs.cid and cs.csid = p.csid and c.cid = 1;
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * Dao层根据订单id查询订单
	 * 
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * Dao层修改订单
	 * 
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		getHibernateTemplate().update(currOrder);
	}

}
