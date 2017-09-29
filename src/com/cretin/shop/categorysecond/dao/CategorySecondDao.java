package com.cretin.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cretin.shop.category.vo.Category;
import com.cretin.shop.categorysecond.vo.CategorySecond;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageHibernateCallback;

/**
 * 二级分类dao
 * 
 * @author cretin
 *
 */
public class CategorySecondDao extends HibernateDaoSupport {

	/**
	 * 查询二级分类的总数
	 * 
	 * @return
	 */
	public int getAllCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * Dao层查询二级分类
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<CategorySecond> getCategorysByPage(int begin, int limit) {
		String hql = "from CategorySecond c order by c.csid desc";
		List<CategorySecond> list = getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * Dao层添加二级分类
	 * 
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		getHibernateTemplate().save(categorySecond);
	}

	/**
	 * Dao层根据id获取二级分类
	 * 
	 * @param csid
	 * @return
	 */
	public CategorySecond getByCsid(Integer csid) {
		return getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * Dao层删除二级分类
	 * 
	 * @param csid
	 * @return
	 */
	public void delete(CategorySecond categorySecond) {
		getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * dao层修改
	 * 
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		getHibernateTemplate().update(categorySecond);
	}

	/**
	 * dao层获取所有的二级分类
	 * 
	 * @return
	 */
	public List<CategorySecond> getAllCategorySecond() {
		String hql = "from CategorySecond";
		return (List<CategorySecond>) getHibernateTemplate().find(hql);
	}
}
