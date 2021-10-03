package com.tokopedia.GroceryBillingApplication;

import java.io.IOException;

import com.tokopedia.GroceryBillingApplication.Controller.TransactionController;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;

public class App {
	
    public static void main( String[] args ) throws IOException{

    	GroceryBilling groceryBilling = new GroceryBilling();
    	
    	groceryBilling.printWelcomeText();

        Transaction transaction = new Transaction();
        
        groceryBilling.checkout(transaction);
        
        groceryBilling.checkMembership(transaction);

    	TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
        transactionController.countOriginalPrice();
        
        transactionController.countMemberDiscountPrice();
        
        transactionController.countTransactionFee();
        
        transactionController.countTotalPrice();
        
        groceryBilling.printRecipt(transaction);
    }
}
