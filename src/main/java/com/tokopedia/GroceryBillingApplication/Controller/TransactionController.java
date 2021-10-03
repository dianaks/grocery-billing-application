package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;

public class TransactionController {
	
	public static final Float MEMBERSHIP_FEE = Float.valueOf(100);
	
	private static final Float MEMBERSHIP_DISCOUNT_PERCENTAGE = (float)0.005;
	private static final Float MEMBERSHIP_FLAT_DISCOUNT = Float.valueOf(10);
	private static final Float MEMBERSHIP_FLAT_MINIMUM = Float.valueOf(100);
	
	private static Transaction transaction;
	
	private GroceryBilling cashier;
	
	public TransactionController(Transaction transaction, GroceryBilling cashier){
     
		this.transaction = transaction;
		this.cashier = cashier;
    }

	public void addToCart(Item item, int qty) throws IOException {
		transaction.getCart().put(item, qty);
	}
	
	public Map<Item, Integer> getCart() {
		return transaction.getCart();
	}
	
	public void deleteCart() {
		transaction.setCart(new HashMap<>());
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void countOriginalPrice() {
		Float originalPrice = Float.valueOf(0);
		
		for (Map.Entry<Item, Integer> item : transaction.getCart().entrySet()) {
			originalPrice += item.getValue() * item.getKey().getPrice();
		}

		transaction.setOriginalPrice(originalPrice);
	}
	
	public void countTransactionFee() {
		
		Float afterDiscountPrice = transaction.getOriginalPrice() - transaction.getDiscountPrice();
		
		Float transactionFee = afterDiscountPrice * transaction.getTransactionFeePercentage();
		
		transactionFee = (float) (Math.round(transactionFee*100.0)/100.0);
				
		transaction.setTransactionFee(transactionFee);
	}
	
	public void countTotalPrice() {
		Float afterDiscountPrice = transaction.getOriginalPrice() - transaction.getDiscountPrice();
		
		Float setTotalPrice = afterDiscountPrice + transaction.getMembershipFee() + transaction.getTransactionFee();
		
		transaction.setTotalPrice((float) (Math.ceil(setTotalPrice *100.0)/100.0));
	}
	
	public void countMemberDiscountPrice() {
		
		Float discountPrice = Float.valueOf(0);
		
		boolean isMember = transaction.getMember().getId() != null;
		boolean isMaxPrice = transaction.getOriginalPrice() >= MEMBERSHIP_FLAT_MINIMUM;
		
		if(isMember) {
			if(isMaxPrice) {
				discountPrice = MEMBERSHIP_FLAT_DISCOUNT;
			} else {
				discountPrice = transaction.getOriginalPrice()* MEMBERSHIP_DISCOUNT_PERCENTAGE;
			}
			
			discountPrice = (float) (Math.round(discountPrice*100.0)/100.0);
		}
		
		transaction.setDiscountPrice(discountPrice);
	}
	

	
}
