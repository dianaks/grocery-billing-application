package com.tokopedia.GroceryBillingApplication.Controller;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.tokopedia.GroceryBillingApplication.Model.Member;

public class MemberController {

	private Member member;

	public MemberController() {
		this.member = new Member();
	}

	public MemberController(Member member) {
		this.member = member;
	}

	public void setMember(Integer id, String name, String phone) {
		this.member = new Member(id, name, phone);
	}

	public Member registerMember() {
		try {
			List<String> data = new ArrayList<>();

			FileReader reader = new FileReader("resource/member.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				data.add(line);
			}

			FileWriter writer = new FileWriter("resource/member.txt");

			data.forEach(n -> {
				try {
					writer.append(n + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			while ((line = bufferedReader.readLine()) != null) {
				writer.append(line);
			}
			writer.append(this.member.getId() + "|" + this.member.getName() + "|" + this.member.getPhone());
			writer.close();
			reader.close();

			System.out.println("Congrats, you're a member now!");

		} catch (IOException e) {
			System.out.println("Failed, please try again");
			e.printStackTrace();
		}

		return this.member;
	}

	public Member fetchMemberData(String memberId) throws IOException, NumberFormatException {

		Integer id = null;

		try {
			id = Integer.parseInt(memberId);
		} catch (NumberFormatException e) {
			System.out.println("Invalid member id!");
		}

		File text = new File("resource/member.txt");

		Scanner scanner = new Scanner(text);

		boolean exist = false;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			String[] arrayData = line.split("|");

			exist = (id == Integer.parseInt(arrayData[0].trim()));

			if (exist) {
				this.member.setId(id);
				this.member.setName(arrayData[1].trim());
				this.member.setPhone(arrayData[2].trim());

				break;
			}
		}

		if (!exist) {
			System.out.println("Member not found!");
		}

		return this.member;
	}
}
