package com.tokopedia.GroceryBillingApplication.helper;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;

public class Generator {

	public static String generateRandomString() {
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));

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
