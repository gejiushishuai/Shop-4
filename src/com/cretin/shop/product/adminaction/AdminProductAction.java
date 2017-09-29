package com.cretin.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.cretin.shop.category.service.CategoryService;
import com.cretin.shop.category.vo.Category;
import com.cretin.shop.categorysecond.service.CategorySecondService;
import com.cretin.shop.categorysecond.vo.CategorySecond;
import com.cretin.shop.product.service.ProductService;
import com.cretin.shop.product.vo.Product;
import com.cretin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * 
 * @author cretin
 *
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 模型驱动
	private Product product = new Product();
	// 注入ProductService
	private ProductService productService;
	// 注入CategorySecondService
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 文件上传需要的三个属性:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	// 获取当前页面page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	/**
	 * 全展示部商品
	 * 
	 * @return
	 */
	public String findAllByPage() {
		// 获取所有商品
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean = productService.findAllByPage(page);
		// 将数据放置到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	/**
	 * 商品添加跳转方法
	 * 
	 * @return
	 */
	public String addPage() {
		/**
		 * 查询所有的二级分类
		 */
		List<CategorySecond> cList = categorySecondService
				.getAllCategorySecond();
		// 将二级分类添加到值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}

	/**
	 * 保存商品的方法
	 * 
	 * @return
	 */
	public String save() {
		// 调用Service完成保存的操作
		product.setPdate(new Date());
		if (upload != null) {
			// 获取文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			// 创建一个文件
			File destFile = new File(realPath + "//" + uploadFileName);
			// 文件上传
			try {
				FileUtils.copyFile(upload, destFile);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("文件上传失败");
			}
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	/**
	 * 删除商品
	 * 
	 * @return
	 */
	public String delete() {
		// 先查询再删除
		product = productService.findByPid(product.getPid());
		// 删除商品
		try {
			productService.delete(product);
			// 先删除后台商品图片
			String path = product.getImage();
			if (path != null && !path.equals("")) {
				String realPath = ServletActionContext.getServletContext()
						.getRealPath(path);
				File file = new File(realPath);
				file.delete();
			}
		} catch (Exception e) {
			addActionError("改商品已经在用户的订单中，暂时不能删除");
			return "adminmsg";
		}
		return "deleteSuccess";
	}

	/**
	 * 编辑商品的方法
	 * 
	 * @return
	 */
	public String edit() {
		// 根据id查询商品
		product = productService.findByPid(product.getPid());
		// 查询所有的二级分类
		List<CategorySecond> csList = categorySecondService
				.getAllCategorySecond();
		// 将数据放置到值栈中
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	/**
	 * 修改商品的方法
	 * 
	 * @return
	 */
	public String update() {
		product.setPdate(new Date());
		if (upload != null ) {
			//先删除文件
			String path = product.getImage();
			if (path != null && !path.equals("")) {
				String realPath = ServletActionContext.getServletContext()
						.getRealPath(path);
				File file = new File(realPath);
				file.delete();
			}
			
			// 获取文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			// 创建一个文件
			File destFile = new File(realPath + "//" + uploadFileName);
			// 文件上传
			try {
				FileUtils.copyFile(upload, destFile);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("文件上传失败");
			}
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
