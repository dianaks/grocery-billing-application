package com.tokopedia.GroceryBillingApplication.Model;

import java.util.List;
import java.util.ArrayList;

public class Transaction {
	private int id;
	private List<Item> cart = new ArrayList<>();
	private boolean isNewMember = false;
	private boolean isMember = false;
	private Long totalPrice;
	private Long originalPrice;
	private Long discountPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Item> getCart() {
		return cart;
	}
	public void setCart(List<Item> cart) {
		this.cart = cart;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	public boolean getIsNewMember() {
		return isNewMember;
	}
	public void setNewMember(boolean isNewMember) {
		this.isNewMember = isNewMember;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Long originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Long getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}
}
