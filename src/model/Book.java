package model;

public class Book {
	private int id;
	private String name;
	private double price;
	private String author;
	private String details;
	private String press;
	
	/**
	 *重写toString方法
	*/
	@Override
	public String toString() {
		return "id:" + this.id + " name:" + this.name + " price:" + this.price + " author:" + this.author + " press:" + this.press+ " details:" + this.details;
	}
	
	/**
	 * 将Book的所有属性组装成一个数组
	 * */
	public String[] getPropertyArray() {
		String[] propertyArray= {Integer.toString(this.id), this.name, Double.toString(this.price), this.author, this.press, this.details};
		return propertyArray;
	}
	
	public Book() {
		super();
	}
	public Book(int id, String name, double price, String author, String details, String press) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.author = author;
		this.details = details;
		this.press = press;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
}
