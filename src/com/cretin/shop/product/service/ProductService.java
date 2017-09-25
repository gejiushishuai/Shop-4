package com.cretin.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cretin.shop.product.dao.ProductDao;
import com.cretin.shop.product.vo.Product;

/**
 * 商品的业务层代码
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
	 * @return
	 */
	public List<Product> getHotProducts() {
		return productDao.getHotProducts();
	}

	/**
	 * 查询最新的商品
	 * @return
	 */
	public List<Product> getNewestProducts() {
		return productDao.getNewestProducts();
	}

	/**
	 * 根据id查询商品
	 * @param pid
	 * @return
	 */
	public Product findByPid(String pid) {
		return productDao.findByPid(pid);
	}
}
