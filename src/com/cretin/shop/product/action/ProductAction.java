package com.cretin.shop.product.action;

import com.cretin.shop.product.service.ProductService;
import com.cretin.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品的Action对象
 * 
 * @author cretin
 *
 */
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 用于接受数据的模型驱动
	private Product product = new Product();
	// 注入ProductService
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	/**
	 * 根据商品的Id查询商品 执行方法
	 * 
	 * @return
	 */
	public String findByPid() {
		// 查询
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
}
