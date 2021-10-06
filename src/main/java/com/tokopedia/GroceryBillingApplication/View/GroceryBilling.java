package com.tokopedia.GroceryBillingApplication.View;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.tokopedia.GroceryBillingApplication.Controller.ItemController;
import com.tokopedia.GroceryBillingApplication.Controller.MemberController;
import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;

public class GroceryBilling {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void printWelcomeText() {
		System.out.println("===========================");
		
		System.out.println("GROCERY BILLING APPLICATION");
		
		System.out.println("by: Elisabeth Diana");
	}
	
	public void printReceipt(Transaction transaction) {
		System.out.println("===========================");
		
		System.out.println("HERE IS YOUR BILLING ... \n");
		
        System.out.println("Original Price: $" + transaction.getOriginalPrice());
        
        System.out.println("Membership Fee: $" + transaction.getMembershipFee());
        
        System.out.println("Discount Price: $" + transaction.getDiscountPrice());
        
        System.out.println("Transaction Fee: $" + transaction.getTransactionFee());
        
        System.out.println("---------------------------");
        
        System.out.println("Total Price: $" + transaction.getTotalPrice());
        
        System.out.println("===========================");
        
		scanner.close();
	}
	
   public String checkMembership() throws IOException {
		System.out.println("===========================");
			
		System.out.println("MEMBER ID: ");
			
		String memberId = scanner.nextLine();
			
		return memberId;
    }
   
   public String confirmMemberRegistration() throws IOException {
		System.out.println("===========================");
			   
		System.out.println("Register as a member?");
			   
		System.out.println("Registration fee $100 (y/N):");
			   
		String answer = scanner.nextLine().trim().toLowerCase();
			   
		return answer;
   }
   
   public Map<String, String> registerMember() throws IOException {
	    System.out.println("===========================");
	   
		Map<String, String> member = new HashMap<>();
		System.out.println("name:");
		member.put("name", scanner.nextLine().trim().toLowerCase());
		
		System.out.println("phone:");
		member.put("phone", scanner.nextLine().trim().toLowerCase());
		
		return member;
   }
    
    public void checkout(Transaction transaction) throws IOException {
    	
    	System.out.println("===========================");
    	
    	ItemController itemController = new ItemController("resource/item.txt");
    	
    	Map<Integer, Item> items = itemController.getAllItemFromFile();
    
    	Map<Item, Integer> cart = new HashMap<>();
		
		System.out.println("ITEM QUANTITY \n");
		 
		for (Map.Entry<Integer, Item> item : items.entrySet()) {
			  
			  System.out.println(item.getValue().getName() + ":"); 
			  
			  int quantity = 0;
			  
			  try {
				  quantity = Integer.parseInt(scanner.nextLine().trim());
				  
				  cart.put(item.getValue(), quantity);
				  
			  } catch (NumberFormatException e) {
				  
				  cart.put(item.getValue(), 0);
			  }
			  
			  System.out.println("------> $" + (item.getValue().getPrice().multiply(BigDecimal.valueOf(quantity)))); 
		}
		
		transaction.setCart(cart);
		
    }
    
    public String choosePaymentMethod(Transaction transaction)  throws IOException {
    	System.out.println("===========================");
    	
    	System.out.println("Choose payment method (CASH/credit card):");
    	
		String answer = scanner.nextLine().trim().toLowerCase();
		
		return answer;
    }
    
    public void printNotification(String message) {
    	System.out.println("---------------------------");
    	
    	System.out.println(message);
	}
}
