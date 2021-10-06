package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;

public class TransactionController {
	
	public static final BigDecimal MEMBERSHIP_FEE = BigDecimal.valueOf(100);
	
	private static final BigDecimal MEMBERSHIP_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(0.05);
	
	private static final BigDecimal MEMBERSHIP_FLAT_DISCOUNT = BigDecimal.valueOf(10);
	
	private static final BigDecimal MEMBERSHIP_FLAT_MINIMUM = BigDecimal.valueOf(100);
	
	private Transaction transaction;
	
	private GroceryBilling groceryBilling;
	
	public TransactionController(Transaction transaction, GroceryBilling groceryBilling){
		
		this.transaction = transaction;
		
		this.groceryBilling = groceryBilling;
    }

	public void addToCart(Item item, int qty) throws IOException {
		transaction.getCart().put(item, qty);
	}
	
	public void addMembershipFee() {
		transaction.setMembershipFee(MEMBERSHIP_FEE);;
	}
	
	public Map<Item, Integer> getCart() {
		return transaction.getCart();
	}
	
	public void deleteCart() {
		transaction.setCart(new HashMap<>());
	}
	
	public void countOriginalPrice() {
		BigDecimal originalPrice = BigDecimal.valueOf(0);
		
		for (Map.Entry<Item, Integer> item : transaction.getCart().entrySet()) {
			originalPrice = originalPrice.add(item.getKey().getPrice().multiply(BigDecimal.valueOf(item.getValue())));
		}

		transaction.setOriginalPrice(originalPrice);
	}
	
	public void countTransactionFee() {
		
		BigDecimal accumulative = transaction.getOriginalPrice().subtract(transaction.getDiscountPrice()).add(transaction.getMembershipFee());
		
		BigDecimal transactionFee = accumulative.multiply(transaction.getTransactionFeePercentage());
		
		transactionFee = transactionFee.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		transaction.setTransactionFee(transactionFee);
	}
	
	public void countTotalPrice() {
		BigDecimal accumulative = transaction.getOriginalPrice().subtract(transaction.getDiscountPrice()).add(transaction.getMembershipFee());
		
		BigDecimal totalPrice = accumulative.add(transaction.getTransactionFee());
		
		totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		transaction.setTotalPrice(totalPrice);
	}
	
	public void countMemberDiscountPrice() {
		
		BigDecimal discountPrice = BigDecimal.valueOf(0);
		
		boolean isMember = transaction.getMember().getId() != null;
		boolean isMaxPrice = transaction.getOriginalPrice().compareTo(MEMBERSHIP_FLAT_MINIMUM) >= 0;
		
		if(isMember) {
			if(isMaxPrice) {
				discountPrice = MEMBERSHIP_FLAT_DISCOUNT;
			} else {
				discountPrice = transaction.getOriginalPrice().multiply(MEMBERSHIP_DISCOUNT_PERCENTAGE);
			}
			
			discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
		
		transaction.setDiscountPrice(discountPrice);
	}
	
	public void checkout() throws IOException {
		groceryBilling.printWelcomeText();
        
        groceryBilling.checkout(this.transaction);
	}
	
	public void printReceipt() {
		groceryBilling.printReceipt(this.transaction);
	}
	
	public void choosePaymentMethod() throws IOException {
		String method = groceryBilling.choosePaymentMethod(this.transaction);
		
		if(method.equals("credit card")) {
			transaction.setTransactionFeePercentage(BigDecimal.valueOf(0.02));
		} else {
			transaction.setTransactionFeePercentage(BigDecimal.valueOf(0));
		}
	}
}
