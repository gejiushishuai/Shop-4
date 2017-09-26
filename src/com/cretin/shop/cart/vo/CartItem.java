package com.cretin.shop.cart.vo;

import com.cretin.shop.product.vo.Product;

/**
 * 购物项对象
 * 
 * @author cretin
 *
 */
public class CartItem {
	private Product product;// 购物项中商品的信息
	private int count;// 购买的商品的数量
	private double subtotal;// 购买某种商品的小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return count * product.getShop_price();
	}

}
