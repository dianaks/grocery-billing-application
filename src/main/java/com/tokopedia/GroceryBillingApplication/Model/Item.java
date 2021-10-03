package com.tokopedia.GroceryBillingApplication.Model;

public class Item {
	private int id;
	private String name;
	private Float price;
	private String unit;
	
	public Item() {
		this.id = 0;
		this.name = "";
		this.price = 0f;
	}
	
	public Item(int id, String name, Float price) {
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
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
