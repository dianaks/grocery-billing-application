package com.tokopedia.GroceryBillingApplication.Model;

import java.math.BigDecimal;

public class Item {
	private int id;
	private String name;
	private BigDecimal price;
	private String unit;
	
	public Item() {
		this.id = 0;
		this.name = "";
		this.price = BigDecimal.valueOf(0);
	}
	
	public Item(int id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
