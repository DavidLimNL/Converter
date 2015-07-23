package nl.david.converter;

public class Category {
	String keyword;
	String name;
	String description;
	
	protected Category(String keyword, String name, String description) {
		this.keyword = keyword;
		this.name = name;
		this.description = description;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected String getKeyword() {
		return keyword;
	}
	protected void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected void printCategory() {
		System.out.println(this.keyword + ", " + this.name + ", " + this.description);
	}
}
