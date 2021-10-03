package com.tokopedia.GroceryBillingApplication.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.tokopedia.GroceryBillingApplication.Controller.TransactionController;
import com.tokopedia.GroceryBillingApplication.Model.Item;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;
import com.tokopedia.GroceryBillingApplication.helper.Generator;


public class GroceryBillingApplicationTest {

	@Test
	void test_newMemberTransactionUnderFlatDiscount() {
		GroceryBilling groceryBilling = new GroceryBilling();
		
		Map<Float, Integer> template = new HashMap<>();
		template.put(2f, 5);

		Transaction transaction = Generator.generateTransaction(Generator.generateCart(template), Generator.generateMember(), true);
		
		TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
        transactionController.countOriginalPrice();
        
        assertEquals(10f, transaction.getOriginalPrice());
        
        transactionController.countMemberDiscountPrice();
        
        assertEquals(0.05f, transaction.getDiscountPrice());
        
        transactionController.countTransactionFee();
        
        assertEquals(0.02f, transaction.getTransactionFee());
        
        transactionController.countTotalPrice();

		assertEquals(109.97f, transaction.getTotalPrice());
	}
	
	@Test
	void test_newMemberTransactionOverFlatDiscount() {
		GroceryBilling groceryBilling = new GroceryBilling();
		
		Map<Float, Integer> template = new HashMap<>();
		template.put(2f, 500);
		
		Transaction transaction = Generator.generateTransaction( Generator.generateCart(template), Generator.generateMember(), true);
		
		TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
        transactionController.countOriginalPrice();
        
        assertEquals(1000f, transaction.getOriginalPrice());
        
        transactionController.countMemberDiscountPrice();
        
        assertEquals(10f, transaction.getDiscountPrice());
        
        transactionController.countTransactionFee();
        
        assertEquals(1.98f, transaction.getTransactionFee());
        
        transactionController.countTotalPrice();

		assertEquals(1091.98f, transaction.getTotalPrice());
	}
	
	@Test
	void test_memberTransactionUnderFlatDiscount() {
		GroceryBilling groceryBilling = new GroceryBilling();

		Map<Float, Integer> template = new HashMap<>();
		template.put(2f, 5);
		
		Transaction transaction = Generator.generateTransaction(Generator.generateCart(template), Generator.generateMember(), false);
		
		TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
        transactionController.countOriginalPrice();
        
        assertEquals(10f, transaction.getOriginalPrice());
        
        transactionController.countMemberDiscountPrice();
        
        assertEquals(0.05f, transaction.getDiscountPrice());
        
        transactionController.countTransactionFee();
        
        assertEquals(0.02f, transaction.getTransactionFee());
        
        transactionController.countTotalPrice();

		assertEquals(9.98f, transaction.getTotalPrice());
	}
	
	@Test
	void test_memberTransactionOverFlatDiscount() {
		GroceryBilling groceryBilling = new GroceryBilling();
		
		Map<Float, Integer> template = new HashMap<>();
		template.put(2f, 500);
		
		Transaction transaction = Generator.generateTransaction(Generator.generateCart(template), Generator.generateMember(), false);
		
		TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
        transactionController.countOriginalPrice();
        
        assertEquals(1000f, transaction.getOriginalPrice());
        
        transactionController.countMemberDiscountPrice();
        
        assertEquals(10f, transaction.getDiscountPrice());
        
        transactionController.countTransactionFee();
        
        assertEquals(1.98f, transaction.getTransactionFee());
        
        transactionController.countTotalPrice();

		assertEquals(991.98f, transaction.getTotalPrice());
	}
	
	@Test
	void test_nonMemberTransaction() {
		GroceryBilling groceryBilling = new GroceryBilling();
		
		Map<Float, Integer> template = new HashMap<>();
		template.put(2f, 50);
		
		Transaction transaction = Generator.generateTransaction(Generator.generateCart(template), new Member(), false);
		
		TransactionController transactionController = new TransactionController(transaction, groceryBilling);
        
        transactionController.countOriginalPrice();
        
        assertEquals(100f, transaction.getOriginalPrice());
        
        transactionController.countMemberDiscountPrice();
        
        assertEquals(0f, transaction.getDiscountPrice());
        
        transactionController.countTransactionFee();
        
        assertEquals(0.2f, transaction.getTransactionFee());
        
        transactionController.countTotalPrice();

		assertEquals(100.2f, transaction.getTotalPrice());
	}
}
