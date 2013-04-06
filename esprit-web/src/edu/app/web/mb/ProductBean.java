package edu.app.web.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;
import edu.app.persistence.Product;

@ManagedBean
@RequestScoped
public class ProductBean {
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	private boolean formDisplayed = false;
	private Product product = new Product();
	private List<Product> products;
	private int categoryId;
	public ProductBean() {
	}
	
	public String doSaveOrUpdateProduct(){
		String navigateTo = null;
		if (categoryId != -1) {
			Category category = catalogServiceLocal.findCategoryById(categoryId);
			product.setCategory(category);
		}
		catalogServiceLocal.saveOrUpdateProduct(product);
		formDisplayed = false;
		return navigateTo;
	}
	public String doDeleteProduct(){
		String navigateTo = null;
		catalogServiceLocal.removeProduct(product);
		formDisplayed = false;
		return navigateTo;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public Product getProduct() {
		return product;
	}

	public List<Product> getProducts() {
		products = catalogServiceLocal.findAllProducts();
		return products;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	

}
