package com.cretin.shop.categorysecond.service;

import java.util.List;

import com.cretin.shop.category.dao.CategoryDao;
import com.cretin.shop.categorysecond.dao.CategorySecondDao;
import com.cretin.shop.categorysecond.vo.CategorySecond;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageBean;

/**
 * 应用层二级分类
 * 
 * @author cretin
 *
 */
public class CategorySecondService {
	// 注入CategorySecondDao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * 业务层查询分页查询二级分类的方法
	 * 
	 * @param page
	 * @return
	 */
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每一页的记录数
		int limit = 12;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = categorySecondDao.getAllCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页面数
		int totalPage = 0;
		totalPage = totalPage % limit == 0 ? (totalCount / limit) : (totalCount
				/ limit + 1);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page - 1) * limit;
		List<CategorySecond> pList = categorySecondDao
				.getCategorysByPage( begin, limit);
		pageBean.setList(pList);
		return pageBean;
	}

	/**
	 * 业务层添加二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	/**
	 * 根据id查询二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond getByCsid(Integer csid) {
		return categorySecondDao.getByCsid(csid);
	}

	/**
	 * 删除二级分类
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * 业务层修改二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
}
