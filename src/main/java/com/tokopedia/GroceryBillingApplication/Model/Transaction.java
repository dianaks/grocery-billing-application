package com.tokopedia.GroceryBillingApplication.Model;

import java.util.Map;
import java.math.BigDecimal;
import java.util.HashMap;

public class Transaction {
	private int id;
	private Map<Item, Integer> cart = new HashMap<>();
	private Member member;
	private BigDecimal totalPrice;
	private BigDecimal originalPrice;
	private BigDecimal discountPrice;
	private BigDecimal membershipFee = BigDecimal.valueOf(0);
	private BigDecimal transactionFee = BigDecimal.valueOf(0);
	private BigDecimal transactionFeePercentage = BigDecimal.valueOf(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
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

	public BigDecimal getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(BigDecimal membershipFee) {
		this.membershipFee = membershipFee;
	}

	public BigDecimal getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}

	public BigDecimal getTransactionFeePercentage() {
		return transactionFeePercentage;
	}

	public void setTransactionFeePercentage(BigDecimal transactionFeePercentage) {
		this.transactionFeePercentage = transactionFeePercentage;
	}
}
