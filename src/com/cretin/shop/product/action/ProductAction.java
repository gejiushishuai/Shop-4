package com.cretin.shop.product.action;

import java.util.List;

import com.cretin.shop.category.service.CategoryService;
import com.cretin.shop.category.vo.Category;
import com.cretin.shop.product.service.ProductService;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
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
	// 接受当前页数
	private int page;
	
	//接受二级分类的id
	private Integer csid;
	
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
	public Integer getCsid() {
		return csid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// 接受分类的cid
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

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

	/**
	 * 根据一级分类的id查询商品
	 * 
	 * @return
	 */
	public String findByCid() {
		// 根据一级分类查询商品 带分页
		PageBean<Product> pageBean = productService.getProductsByPageCid(cid,
				page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	/**
	 * 根据二级分类的id查询商品
	 * 
	 * @return
	 */
	public String findByCsid() {
		// 根据二级分类查询商品 带分页
		PageBean<Product> pageBean = productService.getProductsByPageCsid(csid,
				page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
