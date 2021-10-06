package com.tokopedia.GroceryBillingApplication.Model;

import java.math.BigDecimal;

public class Member {
	private Integer id;
	private String name;
	private String phone;

	public final BigDecimal MEMBERSHIP_FEE = BigDecimal.valueOf(100);
	
	private final static BigDecimal MEMBERSHIP_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(0.5);
	private final static BigDecimal MEMBERSHIP_FLAT_DISCOUNT = BigDecimal.valueOf(10);
	private final static BigDecimal MEMBERSHIP_FLAT_MINIMUM = BigDecimal.valueOf(100);
	
	public Member(){
	     this.id = null;
	     this.name = "";
	     this.phone = "";
	}
	
	public Member(Integer id, String name, String phone){
	     this.id = id;
	     this.name = name;
	     this.phone = phone;
	}

	public static BigDecimal getMembershipDiscountPercentage() {
		return MEMBERSHIP_DISCOUNT_PERCENTAGE;
	}

	public static BigDecimal getMembershipFlatDiscount() {
		return MEMBERSHIP_FLAT_DISCOUNT;
	}

	public static BigDecimal getMembershipFlatMinimum() {
		return MEMBERSHIP_FLAT_MINIMUM;
	}
	
	public BigDecimal getMembershipFee() {
		return MEMBERSHIP_FEE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
