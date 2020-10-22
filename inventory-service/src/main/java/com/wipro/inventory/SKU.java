package com.wipro.inventory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SKU {
	
	private Integer id;
	
	@NotNull(message = "ProductId is Mandatory")
	private Integer productId;
	
	@Size(max = 100,message="name can not be more than 100 characters")
	private String name;
	
	@Size(max = 500,message="Description can not not more than 100 characters")
	private String description;
	
	private Double price;
	
	private Integer count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
