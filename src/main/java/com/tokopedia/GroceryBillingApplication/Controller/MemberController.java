package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;

public class MemberController {
	public static final Long MEMBERSHIP_FEE = (long) 100;
	
	private static final Long MEMBERSHIP_DISCOUNT_PERCENTAGE = (long) 5;
	private static final Long MEMBERSHIP_FLAT_DISCOUNT = (long) 10;
	private static final Long MEMBERSHIP_FLAT_MINIMUM = (long) 100;
	
	private Member member;
		
	public MemberController(Member member){
     
		this.member = member;
    }
	
	public Member registerMember(int id, String name, String phone) {
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setPhone(phone);
		
		return member;
	}
	
	public Long getMemberDiscountPrice(Long originalPrice) {
		if(originalPrice < MEMBERSHIP_FLAT_MINIMUM) {
			return originalPrice * MEMBERSHIP_DISCOUNT_PERCENTAGE;
		} else {
			return MEMBERSHIP_FLAT_DISCOUNT;
		}
	}
	
	public Long getMembershipFee() {
		return MEMBERSHIP_FEE;
	}
	
	public Member getMember (int id) throws IOException {		

		FileReader reader = new FileReader("member.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        
        Member member = new Member();

        while ((line = bufferedReader.readLine()) != null) {
        	
        	String[] arrayData = line.split("|");
        	
            boolean exist = (id == Integer.parseInt(arrayData[0].trim()));
            
            if(exist) {
            	member.setId(id);
            	member.setName(arrayData[1].trim());
            	member.setPhone(arrayData[2].trim());
            	
            	break;
            }
        }
        
        reader.close();
   
		return member;
	}
}
