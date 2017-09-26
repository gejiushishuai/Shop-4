package com.cretin.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;

/**
 * 购物车对象
 * 
 * @author cretin
 *
 */
public class Cart {
	// 购物项的集合 key是商品的id value 购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;// 购物的总计

	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	public double getTotal() {
		return total;
	}


	// 购物车的功能
	// 1、将购物项添加到购物车
	public void addCart(CartItem cItem) {
		int pid = cItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			// 如果存在则计算数量和小计
			CartItem cartItem = map.get(pid);
			cartItem.setCount(cartItem.getCount() + cItem.getCount());
		} else {
			// 如果不存在直接添加
			map.put(pid, cItem);
		}
		// 设置总计
		total = total + cItem.getSubtotal();
	}

	// 2、移除
	public void removeCart(Integer pid) {
		// 移除购物项
		CartItem cartItem = map.remove(pid);
		total = total - cartItem.getSubtotal();
	}

	// 3、清空
	public void clearCart() {
		// 将购物项清空
		map.clear();
		// 将总计设置成0
		total = 0;
	}
}
