package com.cretin.shop.order.adminaction;

import java.util.List;

import com.cretin.shop.order.service.OrderService;
import com.cretin.shop.order.vo.Order;
import com.cretin.shop.order.vo.OrderItem;
import com.cretin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理Action
 * 
 * @author cretin
 *
 */
public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {
	// 模型驱动
	private Order order = new Order();
	// 注入OrderService
	private OrderService orderService;
	// 接受page
	private Integer page;
	// 接受state
	private Integer states;

	public void setStates(Integer states) {
		this.states = states;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		return order;
	}

	/**
	 * 带分页的查询所有订单的方法
	 * 
	 * @return
	 */
	public String findAllByPage() {
		// 查询数据
		PageBean<Order> pageBean = orderService.getAllByPage(page);
		// 放到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	/**
	 * 根据订单的id查询订单项
	 * 
	 * @return
	 */
	public String findOrderItem() {
		// 查询
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// 页面跳转
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}

	/**
	 * 根据订单的id修改订单的状态
	 * 
	 * @return
	 */
	public String updateState() {
		// 根据订单id查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateState";
	}

	/**
	 * 根据状态查询订单状态
	 */
	public String findByState() {
		// 查询数据
		PageBean<Order> pageBean = orderService.findByState(states, page);
		// 放到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByState";
	}
}
