package com.tokopedia.GroceryBillingApplication.Controller.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import com.tokopedia.GroceryBillingApplication.Controller.TransactionController;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;
import com.tokopedia.GroceryBillingApplication.helper.Generator;

public class TransactionControllerTest {
	
	GroceryBilling groceryBilling = new GroceryBilling();
	
	Transaction transaction = new Transaction();
	
	Member member = Generator.generateMember();
	
	BigDecimal error = new BigDecimal(0.01);

	TransactionController transactionController = new TransactionController(transaction, new GroceryBilling());
  
	@Test
	void test_countOriginalPrice() {
		
		Map<BigDecimal, Integer> template = new HashMap<>();
		template.put(BigDecimal.valueOf(2), 1);
		template.put(BigDecimal.valueOf(1), 5);
		template.put(BigDecimal.valueOf(3), 1);
		template.put(BigDecimal.valueOf(2.2), 5);
		template.put(BigDecimal.valueOf(5), 2);
		
		transaction.setCart(Generator.generateCart(template));
		
        transactionController.countOriginalPrice();
        
        assertThat(BigDecimal.valueOf(31), is(closeTo(transaction.getOriginalPrice(), error)));
	}
	
	@Test
	void test_countDiscountPrice_nonMember() {
		
		transaction.setOriginalPrice(BigDecimal.valueOf(100));
		
		transaction.setMember(new Member());

        transactionController.countMemberDiscountPrice();
        
        assertThat(BigDecimal.valueOf(0), is(closeTo(transaction.getDiscountPrice(), error)));
	}
	
	@Test
	void test_countDiscountPrice_flat_member() {
		
		transaction.setOriginalPrice(BigDecimal.valueOf(100));
		
		transaction.setMember(member);

        transactionController.countMemberDiscountPrice();
        
        assertThat(BigDecimal.valueOf(10), is(closeTo(transaction.getDiscountPrice(), error)));
	}
	
	@Test
	void test_countDiscountPrice_percentage_member() {
		
		transaction.setOriginalPrice(BigDecimal.valueOf(10));
		
		transaction.setMember(member);

        transactionController.countMemberDiscountPrice();
        
        assertThat(BigDecimal.valueOf(0.5), is(closeTo(transaction.getDiscountPrice(), error)));
	}
	
	@Test
	void test_countTotalPrice_withoutDiscountPrice() {
		
		transaction.setOriginalPrice(BigDecimal.valueOf(10));
		
		transaction.setDiscountPrice(BigDecimal.valueOf(0));
		
		transaction.setMember(new Member());
		
		transaction.setTransactionFee(BigDecimal.valueOf(0.02));

        transactionController.countTotalPrice();
        
        assertThat(BigDecimal.valueOf(10.02), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_countTotalPrice_withDiscountPriceAndMembershipFee() {
		
		transaction.setOriginalPrice(BigDecimal.valueOf(100));
		
		transaction.setDiscountPrice(BigDecimal.valueOf(10));
		
		transaction.setMember(member);
		
		transaction.setMembershipFee(BigDecimal.valueOf(100));
		
		transaction.setTransactionFee(BigDecimal.valueOf(0.02));

        transactionController.countTotalPrice();
        
        assertThat(BigDecimal.valueOf(190.02), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_countTransactionFee() {
		
		transaction.setOriginalPrice(BigDecimal.valueOf(100));
		
		transaction.setDiscountPrice(BigDecimal.valueOf(10));
		
		transaction.setMember(member);
		
		transaction.setMembershipFee(BigDecimal.valueOf(100));
		
		transaction.setTransactionFeePercentage(BigDecimal.valueOf(0.02));

        transactionController.countTransactionFee();
        
        assertThat(BigDecimal.valueOf(3.80), is(closeTo(transaction.getTransactionFee(), error)));
	}
}
