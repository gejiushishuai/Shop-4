package com.cretin.shop.order.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.cart.vo.Cart;
import com.cretin.shop.cart.vo.CartItem;
import com.cretin.shop.order.service.OrderService;
import com.cretin.shop.order.vo.Order;
import com.cretin.shop.order.vo.OrderItem;
import com.cretin.shop.user.vo.User;
import com.cretin.shop.utils.PageBean;
import com.cretin.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理的Action
 * 
 * @author cretin
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象
	private Order order = new Order();
	// 注入OrderService
	private OrderService orderService;
	// 接受page
	private Integer page;
	// 接受支付通道编码
	private String pd_FrpId;

	// 接受付款成功之后的响应数据
	private String r6_Order;
	private String r3_Amt;

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
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
	 * 生成订单
	 */
	public String save() {
		// 保存数据到数据库
		// 订单数据补全
		order.setOrdertime(new Date());
		order.setState(1);// 1 未付款 2 已付款 没有发货 3 已发货 没有确认收货 4 交易完成;
		// 总计的数据
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			addActionError("亲，您还没有购物，请先去购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			orderItem.setSubtotal(cartItem.getSubtotal());

			order.getOrderItems().add(orderItem);
		}
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			addActionError("亲，您还没有登录，请先登录！");
			return "login";
		}
		order.setUser(existUser);
		// 保存订单到数据库
		orderService.save(order);
		// 清空购物车
		cart.clearCart();
		// 将订单信息显示 到页面上
		return "saveSuccess";
	}

	/**
	 * 我的订单的查询
	 * 
	 * @return
	 */
	public String findOrdersByUid() {
		// 根据用户id查询
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// 调用Service
		PageBean<Order> pageBean = orderService.findOrdersByUid(user.getUid(),
				page);
		// 将数据放置到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findOrdersByUid";
	}

	/**
	 * 根据订单id查询订单
	 * 
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}

	/**
	 * 为订单付款的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String payOrder() throws IOException {
		// 修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 去付款
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString() + ":" + UUID.randomUUID();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/Shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer(
				"https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	/**
	 * 付款成功回调
	 * 
	 * @return
	 */
	public String callBack() {
		// 修改订单的状态为已付款
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order
				.split(":")[0]));
		// 1 未付款 2 已付款 没有发货 3 已发货 没有确认收货 4 交易完成;
		currOrder.setState(2);
		orderService.update(currOrder);
		addActionMessage("订单付款成功，订单编号："
				+ Integer.parseInt(r6_Order.split(":")[0]) + "付款金额：" + r3_Amt);
		return "msg";
	}
	
	/**
	 * 修改订单的状态
	 * @return
	 */
	public String updateState(){
		//根据id查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		//修改状态
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
