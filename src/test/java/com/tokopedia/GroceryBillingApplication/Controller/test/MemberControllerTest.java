package com.tokopedia.GroceryBillingApplication.Controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tokopedia.GroceryBillingApplication.Controller.MemberController;
import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;

public class MemberControllerTest {
	
	private static final String MEMBER_FILE = "member-test.txt";
	
	private static final String MEMBER_FILE_PATH = "target/test-classes/" + MEMBER_FILE;
	
	private static final int MEMBER_ID = 1;
	
	private static final String MEMBER_ID_STRING = "1";
	
	private static final String MEMBER_NAME = "user";
	
	private static final String MEMBER_PHONE = "0";
	
	@Mock private GroceryBilling groceryBilling = new GroceryBilling();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void test_setMemberFile() {
		Member member =  new Member();
		
		MemberController memberController = new MemberController(member, groceryBilling);
		String memberFile = memberController.setMemberFile(MEMBER_FILE_PATH);
		
		assertEquals(MEMBER_FILE_PATH, memberFile);
	}
	
//	@Test
//	void test_registerAndFetchMember() throws IOException {
//		Member member =  new Member(MEMBER_ID, MEMBER_NAME, MEMBER_PHONE);
//		
//		MemberController memberController = new MemberController(member, groceryBilling);
//		
//		FileEditor fileEditor = new FileEditor();
//		
//		fileEditor.createFile(MEMBER_FILE);
//		
//		memberController.setMemberFile(MEMBER_FILE_PATH);
//		
//		memberController.saveToDocument();
//		
//		memberController.fetchMemberData(MEMBER_ID_STRING);
//		
//		assertEquals(member.getId(), MEMBER_ID, 0);
//		
//		assertEquals(member.getName(), MEMBER_NAME);
//		
//		assertEquals(member.getPhone(), MEMBER_PHONE);
//		
//		fileEditor.deleteFile(MEMBER_FILE);
//	}
	
	@Test
	void test_fetchMember_memberNotFound() throws IOException {
		Member member =  new Member();
		
		MemberController memberController = new MemberController(member, groceryBilling);
		
		memberController.fetchMemberData("1000");
		
		assertEquals(null, member.getId());
		
		assertEquals("", member.getName());
		
		assertEquals("", member.getPhone());
	}
	
	@Test
	void test_fetchMember_memberFound() throws IOException {
		
		Member member =  new Member();
		
		MemberController memberController = new MemberController(member, groceryBilling);
		
		memberController.fetchMemberData("1");
		
		assertNotEquals(null, member.getId());
		
		assertNotEquals("", member.getName());
		
		assertNotEquals("", member.getPhone());
	}
	
}
