package com.tokopedia.GroceryBillingApplication.View;

import java.io.IOException;
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
		System.out.println("GROCERY BILLING APPLICATION");
		System.out.println("by: Elisabeth Diana");
		System.out.println("===========================");
	}
	
	public void printRecipt(Transaction transaction) {
		
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
	
   public void checkMembership(Transaction transaction) throws IOException {
		
		System.out.println("MEMBER ID: ");
		
		String memberId = scanner.nextLine();
		
		MemberController memberController = new MemberController();
		
		transaction.setMember(memberController.fetchMemberData(memberId));
		
		if(transaction.getMember() == null || transaction.getMember().getId() == null) {
			System.out.println("Register as a member?");
			System.out.println("Registration fee $100 (y/N):");
			String answer = scanner.nextLine().trim().toLowerCase();
			
			if(answer.equals("y")) {
				System.out.println("name:");
				String name = scanner.nextLine().trim().toLowerCase();
				
				System.out.println("phone:");
				String phone = scanner.nextLine().trim().toLowerCase();
				
				memberController.setMember(Integer.parseInt(memberId), name, phone);
		
				Member member  = memberController.registerMember();
				
				transaction.setMember(member);
				
				transaction.setMembershipFee(member.getMembershipFee());
			}
		}
		
		System.out.println("===========================");
    }
    
    public void checkout(Transaction transaction) throws IOException {
    	
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
			  
			  System.out.println("------> $" + (item.getValue().getPrice() *  quantity)); 
		}
		
		transaction.setCart(cart);
		
		System.out.println("===========================");
    }
}
