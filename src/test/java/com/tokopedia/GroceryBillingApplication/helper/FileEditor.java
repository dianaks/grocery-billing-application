package com.tokopedia.GroceryBillingApplication.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEditor {
	
	private Path source = Paths.get(this.getClass().getResource("/").getPath());
    
	
	public void createFile(String fileName) throws IOException {
		
		Path filePath = Paths.get(source.toAbsolutePath() + "/" + fileName);
	    
	    try {
			Files.createFile(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFile(String fileName) throws IOException {
		
		Path filePath = Paths.get(source.toAbsolutePath() + "/" + fileName);
		
		Files.deleteIfExists(filePath);
	}
}
