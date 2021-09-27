package com.tokopedia.GroceryBillingApplication.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.tokopedia.GroceryBillingApplication.Model.Item;

public class ItemController {
	
	private Item item;
	
	public ItemController(Item item){
     
		this.item = item;
    }
	
	public Item getItem (int id) throws IOException {		

		FileReader reader = new FileReader("item.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        
        Item item = new Item ();

        while ((line = bufferedReader.readLine()) != null) {
        	
        	String[] arrayData = line.split("|");
        	
            boolean exist = (id == Integer.parseInt(arrayData[0].trim()));
            
            if(exist) {
            	item.setId(id);
            	item.setName(arrayData[1].trim());
            	item.setPrice(Long.parseLong(arrayData[2].trim()));
            	
            	break;
            }
        }
        
        reader.close();
   
		return item;
	}
}
