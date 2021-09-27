package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;

public class TransactionController {
	
	private static final int TRANSACTION_FEE_PERCENTAGE = 2;
	
	private Map<Item, Integer> items = new HashMap<>();
	
	private Transaction transaction;
	
	public TransactionController(Transaction transaction){
     
		this.transaction = transaction;
    }

	public void addToCart(Item item, int qty) throws IOException {
		items.put(item, qty);
	}
	
	public Map<Item, Integer> getCart() {
		return items;
	}
	
	public void deleteCart() {
		 items = new HashMap<>();
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void countOriginalPrice() {
		Long originalPrice = Long.valueOf(0);
		
		for (Map.Entry<Item, Integer> item : items.entrySet()) {
			originalPrice += item.getValue() * item.getKey().getPrice();
		}

		transaction.setOriginalPrice(originalPrice);
	}
	
	public void countTotalPrice(Long membershipFee, Long membershipDiscount) {
		
		Long priceAfterDiscount = transaction.getOriginalPrice() * membershipDiscount;
		
		Long transactionFee = priceAfterDiscount * TRANSACTION_FEE_PERCENTAGE;
		
		transaction.setTotalPrice(priceAfterDiscount - transactionFee + membershipFee);
	}
	
}
