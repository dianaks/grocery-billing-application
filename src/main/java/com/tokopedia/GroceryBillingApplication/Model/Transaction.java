package com.tokopedia.GroceryBillingApplication.Model;

import java.util.Map;
import java.util.HashMap;

public class Transaction {
	private int id;
	private Map<Item, Integer> cart = new HashMap<>();
	private Member member;
	private Float totalPrice;
	private Float originalPrice;
	private Float discountPrice;
	private Float membershipFee = Float.valueOf(0);
	private Float transactionFee;
	private final Float TRANSACTION_FEE_PERCENTAGE = (float) 0.002;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Float getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Map<Item, Integer> getCart() {
		return cart;
	}
	public void setCart(Map<Item, Integer> cart) {
		this.cart = cart;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Float getMembershipFee() {
		return membershipFee;
	}
	public void setMembershipFee(Float membershipFee) {
		this.membershipFee = membershipFee;
	}
	public Float getTransactionFee() {
		return transactionFee;
	}
	public void setTransactionFee(Float transactionFee) {
		this.transactionFee = transactionFee;
	}
	public Float getTransactionFeePercentage() {
		return TRANSACTION_FEE_PERCENTAGE;
	}
}
