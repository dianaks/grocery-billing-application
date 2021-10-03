package com.tokopedia.GroceryBillingApplication.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;

public class Generator {

	public static String generateRandomString() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}

	public static Map<Item, Integer> generateCart(Map<Float, Integer> template) {
		
		Map<Item, Integer> items = new HashMap<>();
		
		for(Map.Entry<Float, Integer> entry: template.entrySet()) {
			items.put(new Item(1, generateRandomString() , entry.getKey()), entry.getValue());
		}

		return items;
	}
	
	public static Member generateMember() {
		Member member = new Member();
		
		member.setId(1);
		member.setName("User");
		member.setPhone("085");
		
		return member;
	}
	
	public static Transaction generateTransaction(Map<Item, Integer> cart, Member member, Boolean isNewMember) {
		Transaction transaction = new Transaction();
		
		transaction.setId(1);
		transaction.setCart(cart);
		transaction.setMember(member);
		
		if(isNewMember) {
			transaction.setMembershipFee(100f);
		}
		
		return transaction;
	}
}
