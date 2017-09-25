package com.cretin.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cretin.shop.category.dao.CategoryDao;
import com.cretin.shop.category.vo.Category;

/**
 * 一级分类的业务层对象
 * 
 * @author cretin
 *
 */
@Transactional
public class CategoryService {
	//注入CategoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 查询所有一级分类的方法
	 * @return
	 */
	public List<Category> getAllCategory() {
		return categoryDao.getAllCategory();
	}
}
