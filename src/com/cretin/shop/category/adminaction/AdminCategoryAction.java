package com.cretin.shop.category.adminaction;

import java.util.List;

import com.cretin.shop.category.service.CategoryService;
import com.cretin.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理的Action
 * 
 * @author cretin
 *
 */
public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	// 模型驱动
	private Category category = new Category();
	// 注入以及分类的Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		return category;
	}

	/**
	 * 后台执行查询所有一级分类的方法
	 * 
	 * @return
	 */
	public String findAll() {
		// 获取所有的以及分类
		List<Category> cList = categoryService.getAllCategory();
		// 将集合的数据显示到页面
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	/**
	 * 后台保存以一级分类的方法
	 * 
	 * @return
	 */
	public String save() {
		// 调用Service进行保存
		categoryService.save(category);
		// 页面跳转
		return "saveSuccess";
	}

	/**
	 * 删除指定id的一级分类
	 * 
	 * @return
	 */
	public String delete() {
		// 调用Service删除一级分类 先查询一级分类下面的二级分类 再进行删除
		category = categoryService.findByCid(category.getCid());
		// 删除
		categoryService.delete(category);
		// 页面跳转
		return "deleteSuccess";
	}

	/**
	 * 编辑指定id的一级分类
	 * 
	 * @return
	 */
	public String edit() {
		// 根据一级分类的id查询一级分类
		category = categoryService.findByCid(category.getCid());
		// 页面跳转
		return "editSuccess";
	}
	
	/**
	 * 修改一级分类的方法
	 * @return
	 */
	public String update(){
		//调用业务层修改一级分类
		categoryService.update(category);
		return "updateSuccess";
	}
}
