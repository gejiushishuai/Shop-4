package com.cretin.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.cart.vo.Cart;
import com.cretin.shop.cart.vo.CartItem;
import com.cretin.shop.product.service.ProductService;
import com.cretin.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车Action
 * 
 * @author cretin
 *
 */
public class CartAction extends ActionSupport {
	// 注入商品Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 接收页面的pid
	private Integer pid;

	// 接收数量count
	private Integer count;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	// 将购物项添加到购物车
	public String addCart() {
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		// 添加到购物车
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}

	/**
	 * 清空购物车
	 */
	public String clearCart() {
		getCart().clearCart();
		return "clearCart";
	}

	/**
	 * 获得购物车
	 * 
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}

	/**
	 * 打开购物车
	 */
	public String openCart() {
		return "openCart";
	}

	/**
	 * 从购物车中移除
	 * 
	 * @return
	 */
	public String removeCart() {
		getCart().removeCart(pid);
		return "removeCart";
	}
}
