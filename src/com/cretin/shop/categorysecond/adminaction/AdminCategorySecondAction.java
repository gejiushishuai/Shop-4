package com.cretin.shop.categorysecond.adminaction;

import java.util.List;

import com.cretin.shop.category.service.CategoryService;
import com.cretin.shop.category.vo.Category;
import com.cretin.shop.categorysecond.service.CategorySecondService;
import com.cretin.shop.categorysecond.vo.CategorySecond;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类管理Action
 * 
 * @author cretin
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {
	// 模型驱动
	private CategorySecond categorySecond = new CategorySecond();
	// 注入CategorySecondService
	private CategorySecondService categorySecondService;
	// 注入以及分类的Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	// 接受page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * 查询所有的二级分类
	 * 
	 * @return
	 */
	public String findAllByPage() {
		// 获取到pageBean
		PageBean<CategorySecond> pageBean = categorySecondService
				.findAllByPage(page);
		// 保存数据到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	/**
	 * 跳转到添加二级分类的页面
	 * 
	 * @return
	 */
	public String addPage() {
		// 查询所有以及分类
		List<Category> cList = categoryService.getAllCategory();
		// 将以及分类放置到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}

	/**
	 * 添加二级分类
	 * 
	 * @return
	 */
	public String save() {
		// 添加二级分类
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/**
	 * 删除二级分类
	 * 
	 * @return
	 */
	public String delete() {
		// 根据id查询二级分类
		categorySecond = categorySecondService.getByCsid(categorySecond
				.getCsid());
		// 删除二级分类
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	/**
	 * 跳转到二级分类
	 * 
	 * @return
	 */
	public String edit() {
		// 根据id查询二级分类
		categorySecond = categorySecondService.getByCsid(categorySecond
				.getCsid());
		// 查询所有的一级分类
		List<Category> cList = categoryService.getAllCategory();
		// 将以及分类放置到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	/**
	 * 修改二级分类
	 * 
	 * @return
	 */
	public String update() {
		// 修改二级分类
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
