package edu.app.web.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;
import edu.app.persistence.Product;

@ManagedBean
@RequestScoped
public class CategoryBean {
	
	private boolean formDisplayed = false;
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	
	private List<Category> categories;
	private Category category = new Category();
	private  List<SelectItem> categoriesSelectItems;
	
	public CategoryBean() {
	}
	
	
	public String doSaveOrUpdateCategory(){
		String navigateTo = null;
		List<Product> products = catalogServiceLocal.findProductsByCategory(category);
		category.setProducts(products);
		catalogServiceLocal.saveOrUpdateCategory(category);
		formDisplayed = false;
		return navigateTo;
	}
	public String doDeleteCategory(){
		String navigateTo = null;
		List<Product> products = catalogServiceLocal.findProductsByCategory(category);
		category.setProducts(products);
		catalogServiceLocal.removeCategory(category);
		formDisplayed = false;
		return navigateTo;
	}

	public List<Category> getCategories() {
		categories = catalogServiceLocal.findAllCategories();
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}


	public List<SelectItem> getCategoriesSelectItems() {
		categories = catalogServiceLocal.findAllCategories();
		categoriesSelectItems = new ArrayList<SelectItem>(categories.size()+1);
		categoriesSelectItems.add(new SelectItem(-1, "choose one..."));
		for (Category c:categories) {
			categoriesSelectItems.add(new SelectItem(c.getId(), c.getName()));
		}
		return categoriesSelectItems;
	}


	public void setCategoriesSelectItems(List<SelectItem> categoriesSelectItems) {
		this.categoriesSelectItems = categoriesSelectItems;
	}

	

}
