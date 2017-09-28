package com.cretin.shop.product.adminaction;

import com.cretin.shop.product.service.ProductService;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * 
 * @author cretin
 *
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 模型驱动
	private Product product = new Product();
	// 注入ProductService
	private ProductService productService;
	//获取当前页面page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	/**
	 * 全展示部商品
	 * 
	 * @return
	 */
	public String findAllByPage() {
		// 获取所有商品
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean = productService.findAllByPage(page);
		// 将数据放置到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

}
