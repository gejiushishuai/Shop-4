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

	/**
	 * 业务层保存一级分类的方法
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	/**
	 * 根据cid查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 * 业务层删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * 业务层修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}

}
