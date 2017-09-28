package com.cretin.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cretin.shop.order.dao.OrderDao;
import com.cretin.shop.order.vo.Order;
import com.cretin.shop.utils.PageBean;

/**
 * 订单模块业务层
 * 
 * @author cretin
 *
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 保存订单
	 * 
	 * @param order
	 */
	public void save(Order order) {
		orderDao.save(order);
	}

	/**
	 * 根据用户id查询订单
	 * 
	 * @param uid
	 * @param page
	 * @return
	 */
	public PageBean<Order> findOrdersByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示数
		Integer limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数
		Integer totalCount = null;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置数据集合
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findOrdersByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据订单id查询订单
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	/**
	 * 业务层修改订单信息
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}
}
