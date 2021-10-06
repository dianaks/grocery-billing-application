package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.tokopedia.GroceryBillingApplication.Model.Member;
import com.tokopedia.GroceryBillingApplication.View.GroceryBilling;

public class MemberController {

	private Member member;
	
	private GroceryBilling groceryBilling;
	
	private String memberFile = "resource/member.txt";
	
	private String newMemberId;

	public MemberController(Member member, GroceryBilling groceryBilling) {
		this.member = member;
		this.groceryBilling = groceryBilling;
	}

	public Member setMember(Integer id, String name, String phone) {
		this.member = new Member(id, name, phone);
		
		return this.member;
	}
	
	public String setMemberFile(String memberFile) {
		this.memberFile = memberFile;
		
		return this.memberFile;
	}

	public Member saveToDocument() {
		try {
			List<String> data = new ArrayList<>();

			FileReader reader = new FileReader(memberFile);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				data.add(line);
			}

			FileWriter writer = new FileWriter(memberFile);

			data.forEach(n -> {
				try {
					writer.append(n + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			while ((line = bufferedReader.readLine()) != null) {
				writer.append(line);
			}
			writer.append(this.member.getId() + "|" + this.member.getName() + "|" + this.member.getPhone());
			writer.close();
			reader.close();

			groceryBilling.printNotification("Congrats, you're a member now!");

		} catch (IOException e) {
			groceryBilling.printNotification("Failed, please try again");
			e.printStackTrace();
		}

		return this.member;
	}

	public Member fetchMemberData(String memberId) throws IOException, NumberFormatException {

		Integer id = null;

		try {
			id = Integer.parseInt(memberId);
		} catch (NumberFormatException e) {
			groceryBilling.printNotification("Invalid member id!");
		}

		File text = new File(memberFile);

		Scanner scanner = new Scanner(text);

		boolean exist = false;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			String[] arrayData = line.split("\\|");

			exist = (id == Integer.parseInt(arrayData[0].trim()));

			if (exist) {
				this.member.setId(id);
				this.member.setName(arrayData[1].trim());
				this.member.setPhone(arrayData[2].trim());

				break;
			}
		}

		if (!exist) {
			groceryBilling.printNotification("Member not found!");
		}

		return this.member;
	}
	
	public Boolean checkMembership() throws IOException {
		this.newMemberId = groceryBilling.checkMembership();
		
		this.fetchMemberData(newMemberId);
		
		return member != null && member.getId() != null;
	}
	
	public Boolean confirmMemberRegistration() throws IOException {
	
		String answer = groceryBilling.confirmMemberRegistration();
		
		if(answer.equals("y")) {
			return true;		
		} else {
			return false;
		}
	}
	
	public Member registerMember() throws IOException {
		Map<String, String> buyer = groceryBilling.registerMember();
		
		this.member.setId(Integer.parseInt(newMemberId));
		
		this.member.setName(buyer.get("name"));
		
		this.member.setPhone(buyer.get("phone"));
				
		saveToDocument();
		
		return this.member;
	}
}
