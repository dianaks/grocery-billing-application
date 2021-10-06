package com.tokopedia.GroceryBillingApplication;

import java.io.IOException;

import com.tokopedia.GroceryBillingApplication.Controller.MemberController;
import com.tokopedia.GroceryBillingApplication.Controller.TransactionController;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;

public class App {
	
    public static void main( String[] args ) {
    	GroceryBilling groceryBilling = new GroceryBilling();
    	
    	Member member = new Member();
    	
    	MemberController memberController = new MemberController(member, groceryBilling);
    	
    	Transaction transaction = new Transaction();
    	
    	transaction.setMember(member);
    	
    	TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
    	try {
			billing(transactionController, transaction, memberController, member);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
 
    public static void billing(
    		TransactionController transactionController, 
    		Transaction transaction,
    		MemberController memberController,
        	Member member
        	) throws IOException {
    	
    	// Checkout cart
    	
    	transactionController.checkout();
    	
    	
    	// Membership
        
    	Boolean isMember = null;
    	
    	isMember = memberController.checkMembership();
    	
    	if(!isMember) {
    		
    		Boolean isAgree = null;
    		
    		isAgree = memberController.confirmMemberRegistration();
    		
        	if(isAgree) {
        		memberController.registerMember();
        		
				transactionController.addMembershipFee();
        	}
    	}
    	
    	// Payment Method
    	transactionController.choosePaymentMethod();
        
    	// Total Price Calculation
    	
        transactionController.countOriginalPrice();
        
        transactionController.countMemberDiscountPrice();
        
        transactionController.countTransactionFee();
        
        transactionController.countTotalPrice();
        
        // Print Receipt
        
        transactionController.printReceipt();
    }
}
