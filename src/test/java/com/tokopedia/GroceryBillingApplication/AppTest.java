package com.tokopedia.GroceryBillingApplication;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tokopedia.GroceryBillingApplication.Controller.MemberController;
import com.tokopedia.GroceryBillingApplication.Controller.TransactionController;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.Model.Transaction;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;
import com.tokopedia.GroceryBillingApplication.helper.FileEditor;
import com.tokopedia.GroceryBillingApplication.helper.Generator;


/*
 * E2e test 
 */
public class AppTest {
	
	private static final BigDecimal error = new BigDecimal(0.001);
	
	private static final String MEMBER_TEST_FILE = "member-test.txt";
	
	private static final String MEMBER_TEST_FILE_PATH = "target/test-classes/" + MEMBER_TEST_FILE;
	
	private static final String MEMBER_FILE_PATH = "resource/member.txt";
	
	private static final String CASH = "cash";
	
	private static final String CREDIT_CARD = "credit card";
	
	private static final String RESPONSE_YES = "y";
	
	private static final String RESPONSE_NO = "n";
	
	private static final String MEMBER_ID_FOUND = "1";
	
	private static final String MEMBER_ID_NOT_FOUND = "1000";
	
	private Transaction transaction;
	
	private TransactionController transactionController;
	
	private Member member;
	
	private MemberController memberController;
	
	@Mock private GroceryBilling groceryBilling = new GroceryBilling();
	
	FileEditor fileEditor = new FileEditor();
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	private void prepareTransaction(Map<BigDecimal, Integer> cart, Boolean isNewMember) {
		
		this.member = new Member();
				
		this.transaction = Generator.generateTransaction(Generator.generateCart(cart), member, isNewMember);
				
		this.transactionController = new TransactionController(transaction, groceryBilling);
		
		this.memberController = new MemberController(member, groceryBilling);
	}
	
	// Mock buyer response
	private void prepareMock(String payment, String memberId, String registration) throws IOException {
		
		Mockito.when(groceryBilling.choosePaymentMethod(Mockito.any(Transaction.class))).thenReturn(payment);
		
		Mockito.when(groceryBilling.checkMembership()).thenReturn(memberId);
		
		Mockito.when(groceryBilling.confirmMemberRegistration()).thenReturn(registration);
		
		Map<String, String> buyer = new HashMap<>();
		buyer.put("name", "user-test");
		buyer.put("phone", "0");
		
		Mockito.when(groceryBilling.registerMember()).thenReturn(buyer);
	}
	
	private void createMemberTestFile() throws IOException {
		
		fileEditor.createFile(MEMBER_TEST_FILE);
		
		memberController.setMemberFile(MEMBER_TEST_FILE_PATH);
	}
	
	private void deleteMemberTestFile() throws IOException {
		
		fileEditor.deleteFile(MEMBER_TEST_FILE);
		
		memberController.setMemberFile(MEMBER_FILE_PATH);
	}
	
	private Map<BigDecimal, Integer> generateMultipleItemCart(Integer qty) {
		Map<BigDecimal, Integer> cart = new HashMap<>();
		cart.put(BigDecimal.valueOf(2), qty);
		cart.put(BigDecimal.valueOf(3), qty);
		cart.put(BigDecimal.valueOf(4), qty);
		cart.put(BigDecimal.valueOf(5), qty);
		cart.put(BigDecimal.valueOf(2.2), qty);
		
		return cart;
	}
	
	private Map<BigDecimal, Integer> generateSingleItemCart(Integer qty) {
		Map<BigDecimal, Integer> cart = new HashMap<>();
		cart.put(BigDecimal.valueOf(2), qty);
		
		return cart;
	}
	
	/*
	 * Test type: Positive
	 * User type: New Member
	 * */
	
	@Test
	void test_newMember_singleItem_flatDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_NOT_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateSingleItemCart(100), true);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(290), is(closeTo(transaction.getTotalPrice(), error)));
		
		this.deleteMemberTestFile();
	}
	
	@Test
	void test_newMember_singleItem_percentageDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_NOT_FOUND, RESPONSE_YES);
		
		this.prepareTransaction(this.generateSingleItemCart(5), true);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(109.5), is(closeTo(transaction.getTotalPrice(), error)));
		
		this.deleteMemberTestFile();
	}
	
	@Test
	void test_newMember_singleItem_flatDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_NOT_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateSingleItemCart(100), true);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(295.8), is(closeTo(transaction.getTotalPrice(), error)));
		
		this.deleteMemberTestFile();
	}
	
	@Test
	void test_newMember_singleItem_percentageDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_NOT_FOUND, RESPONSE_YES);
		
		this.prepareTransaction(this.generateSingleItemCart(5), true);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(111.69), is(closeTo(transaction.getTotalPrice(), error)));
		
		this.deleteMemberTestFile();
	}
	
	void test_newMember_multipleItem_flatDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_NOT_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateMultipleItemCart(10), false);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(252), is(closeTo(transaction.getTotalPrice(), error)));
	
		this.deleteMemberTestFile();
	}
	
	@Test
	void test_newMember_multipleItem_percentageDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_NOT_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateMultipleItemCart(1), false);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(115.39), is(closeTo(transaction.getTotalPrice(), error)));
	
		this.deleteMemberTestFile();
	}
	
	@Test
	void test_newMember_multipleItem_flatDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_NOT_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateMultipleItemCart(10), false);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(257.04), is(closeTo(transaction.getTotalPrice(), error)));
	
		this.deleteMemberTestFile();
	}
	
	@Test
	void test_newMember_multipleItem_percentageDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_NOT_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateMultipleItemCart(1), false);
		
		this.createMemberTestFile();
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(117.70), is(closeTo(transaction.getTotalPrice(), error)));
		
		this.deleteMemberTestFile();
	}
	
	/*
	 * Test type: Positive
	 * User type: Member
	 * */
	
	@Test
	void test_member_singleItem_flatDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateSingleItemCart(100), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(190), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_member_singleItem_percentageDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateSingleItemCart(5), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(9.5), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_member_singleItem_flatDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateSingleItemCart(100), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(193.8), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_member_singleItem_percentageDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateSingleItemCart(5), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(9.69), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	void test_member_multipleItem_flatDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_FOUND, RESPONSE_YES);

		this.prepareTransaction(this.generateMultipleItemCart(10), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(152), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_member_multipleItem_percentageDiscount_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateMultipleItemCart(1), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(15.39), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void newMember() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateMultipleItemCart(10), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(155.04), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_member_multipleItem_percentageDiscount_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateMultipleItemCart(1), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(15.70), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	/*
	 * Test type: Positive
	 * User type: Non Member
	 * */
	
	@Test
	void test_nonMember_singleItem_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_NOT_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateSingleItemCart(5), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(10), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_nonMember_mutlipleItem_cash() throws IOException {
		
		this.prepareMock(CASH, MEMBER_ID_NOT_FOUND, RESPONSE_NO);
		
		this.prepareTransaction(this.generateMultipleItemCart(10), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(162), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_nonMember_singleItem_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_NOT_FOUND, RESPONSE_NO);

		this.prepareTransaction(this.generateSingleItemCart(5), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(10.2), is(closeTo(transaction.getTotalPrice(), error)));
	}
	
	@Test
	void test_nonMember_mutlipleItem_creditCard() throws IOException {
		
		this.prepareMock(CREDIT_CARD, MEMBER_ID_NOT_FOUND, RESPONSE_NO);
		
		this.prepareTransaction(this.generateMultipleItemCart(10), false);
		
		App.billing(
				this.transactionController,
				this.transaction,
				this.memberController,
				this.member
				);
		
		assertThat(BigDecimal.valueOf(165.24), is(closeTo(transaction.getTotalPrice(), error)));
	}
}
