package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class T7 {
	public static void main(String[] args) throws Exception {
		String filePath = "src\\io";
		String fileName = "IO20.java";
		
		File dir = new File(filePath);
		File file = new File(dir, fileName);
		
//		System.out.println(file.getPath());
//		System.out.println(file.getAbsolutePath());
		
//		BufferedReader in = 
//				new BufferedReader(
//						new InputStreamReader(
//								new FileInputStream(file)));
		
		BufferedReader in = 
				new BufferedReader(
						new FileReader(file));
		
		String input;
		int i=0;
		while((input = in.readLine()) != null) {
			System.out.println(++i + ": " + input);
		}
		
		in.close();
	}
}
