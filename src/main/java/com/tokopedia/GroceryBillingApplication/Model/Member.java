package com.tokopedia.GroceryBillingApplication.Model;

public class Member {
	private Integer id;
	private String name;
	private String phone;

	public final Float MEMBERSHIP_FEE = Float.valueOf(100);
	
	private final static Float MEMBERSHIP_DISCOUNT_PERCENTAGE = (float)0.5;
	private final static Float MEMBERSHIP_FLAT_DISCOUNT = Float.valueOf(10);
	private final static Float MEMBERSHIP_FLAT_MINIMUM = Float.valueOf(100);
	
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

	public static Float getMembershipDiscountPercentage() {
		return MEMBERSHIP_DISCOUNT_PERCENTAGE;
	}

	public static Float getMembershipFlatDiscount() {
		return MEMBERSHIP_FLAT_DISCOUNT;
	}

	public static Float getMembershipFlatMinimum() {
		return MEMBERSHIP_FLAT_MINIMUM;
	}
	
	public Float getMembershipFee() {
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
