package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tokopedia.GroceryBillingApplication.Model.Item;

public class ItemController {

	private String file;

	public ItemController(String file) {

		this.file = file;
	}

	public Map<Integer, Item> getAllItemFromFile() throws IOException {

		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;

		Map<Integer, Item> items = new HashMap<>();

		while ((line = bufferedReader.readLine()) != null) {

			Item item = new Item();

			String[] arrayData = line.split("\\|");

			item.setId(Integer.parseInt(arrayData[0].trim()));
			item.setName(arrayData[1].trim());
			item.setPrice(BigDecimal.valueOf(Double.valueOf(arrayData[2].trim())));

			items.put(item.getId(), item);
		}

		reader.close();

		return items;

	}

	public Map<Item, Integer> getAllItemFromUser() throws IOException {
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;

		Map<Item, Integer> items = new HashMap<>();

		while ((line = bufferedReader.readLine()) != null) {

			Item item = new Item();

			String[] arrayData = line.split("|");

			item.setId(Integer.parseInt(arrayData[0].trim()));
			item.setName(arrayData[1].trim());
			item.setPrice(BigDecimal.valueOf(Double.valueOf(arrayData[2].trim())));

			items.put(item, item.getId());
		}

		reader.close();

		return items;
	}

	public Item getItem(int id) throws IOException {

		FileReader reader = new FileReader("item.txt");
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;

		Item item = new Item();

		while ((line = bufferedReader.readLine()) != null) {

			String[] arrayData = line.split("|");

			boolean exist = (id == Integer.parseInt(arrayData[0].trim()));

			if (exist) {
				item.setId(id);
				item.setName(arrayData[1].trim());
				item.setPrice(BigDecimal.valueOf(Double.valueOf(arrayData[2].trim())));

				break;
			}
		}

		reader.close();

		return item;
	}
}
