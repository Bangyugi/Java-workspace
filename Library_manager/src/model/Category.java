package model;

public class Category {
	String categoryName;
	
	public Category() {
		super();
	}
	public Category( String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + "]";
	}
	
}
