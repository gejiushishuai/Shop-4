package com.cretin.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cretin.shop.product.dao.ProductDao;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageBean;

/**
 * 商品的业务层代码
 * 
 * @author cretin
 *
 */
@Transactional
public class ProductService {
	// 注入ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 查询热门商品
	 * 
	 * @return
	 */
	public List<Product> getHotProducts() {
		return productDao.getHotProducts();
	}

	/**
	 * 查询最新的商品
	 * 
	 * @return
	 */
	public List<Product> getNewestProducts() {
		return productDao.getNewestProducts();
	}

	/**
	 * 根据id查询商品
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	/**
	 * 根据一级分类查询商品 带分页
	 * 
	 * @param cid
	 * @param page
	 * @return
	 */
	public PageBean<Product> getProductsByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每一页的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.getAllCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页面数
		int totalPage = 0;
		totalPage = totalPage % limit == 0 ? (totalCount / limit) : (totalCount
				/ limit + 1);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page - 1) * limit;
		List<Product> pList = productDao
				.getProductsByPageCid(cid, begin, limit);
		pageBean.setList(pList);
		return pageBean;
	}

	/**
	 * 根据二级分类查询商品 带分页
	 * 
	 * @param csid
	 * @param page
	 * @return
	 */
	public PageBean<Product> getProductsByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每一页的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.getAllCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页面数
		int totalPage = 0;
		totalPage = totalCount % limit == 0 ? (totalCount / limit) : (totalCount
				/ limit + 1);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page - 1) * limit;
		List<Product> pList = productDao
				.getProductsByPageCsid(csid, begin, limit);
		pageBean.setList(pList);
		System.out.println(pageBean.toString());
		return pageBean;
	}
}
