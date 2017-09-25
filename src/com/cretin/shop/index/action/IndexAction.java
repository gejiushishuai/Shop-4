package com.cretin.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cretin.shop.category.service.CategoryService;
import com.cretin.shop.category.vo.Category;
import com.cretin.shop.product.service.ProductService;
import com.cretin.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * 
 * @author Cretin
 *
 */
public class IndexAction extends ActionSupport {
	// 注入一级分类的Service
	private CategoryService categoryService;
	//注入商品Service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 执行访问首页的方法
	 */
	public String execute() {
		//查询一级分类
		List<Category> cList = categoryService.getAllCategory();
		//将查询到的一级分类放到Session
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品
		List<Product> hotProducts = productService.getHotProducts();
		System.out.println(hotProducts);
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hotProducts", hotProducts);
		
		//查询最新的商品
		List<Product> newestProducts = productService.getNewestProducts();
		System.out.println(hotProducts);
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("newestProducts", newestProducts);
		return "index";
	}
}
