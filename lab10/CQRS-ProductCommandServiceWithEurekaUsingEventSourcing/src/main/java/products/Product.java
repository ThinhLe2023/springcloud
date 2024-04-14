package products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

	private String id;
	private String name;
	private String productNumber;
	private double price;
	
	public Product(String name, String productNumber, double price) {
		super();
		this.name = name;
		this.productNumber = productNumber;
		this.price = price;
	}
	public Product() {

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
