package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class T5 {
	
	private static void copyDir(File sDir, File dDir) throws Exception {
		if(!dDir.exists()) {
			dDir.mkdirs();
		}
		
		String[] list = sDir.list();
		
		for(int i=0;i<list.length;i++) {
			File sFile = new File(sDir, list[i]);
			File dFile = new File(dDir, list[i]);
			
			if(sFile.isDirectory()) {
				copyDir(sFile, dFile);
			}
			
			BufferedReader in = 
					new BufferedReader(
							new InputStreamReader(
									new FileInputStream(sFile)));
			
			PrintWriter out = 
					new PrintWriter(
							new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream(dFile))));
			
			String input;
			while((input = in.readLine()) != null) {
				out.println(input);
			}
			
			in.close();
			out.close();
			
			Thread.sleep(1000);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		System.out.println("프로그램시작");
		
		String dirSrcName = 
				"C:\\dev\\java_se\\re\\JAVA_SE_Day_19\\src\\io";
		String dirDescName = 
				"C:\\dev\\java_se\\re\\JAVA_SE_Day_19\\src\\io2";
		
		
		File sDir = new File(dirSrcName);
		File dDir = new File(dirDescName);
		
		if(!dDir.exists()) {
			dDir.mkdirs();
		}
		
		String[] list = sDir.list();
		
		for(int i=0;i<list.length;i++) {
			File sFile = new File(sDir, list[i]);
			File dFile = new File(dDir, list[i]);
			
			if(sFile.isDirectory()) {
				copyDir(sFile, dFile);
			} else {
				BufferedReader in = 
						new BufferedReader(
								new InputStreamReader(
										new FileInputStream(sFile)));
				
				PrintWriter out = 
						new PrintWriter(
								new BufferedWriter(
										new OutputStreamWriter(
												new FileOutputStream(dFile))));
				
				String input;
				while((input = in.readLine()) != null) {
					out.println(input);
				}
				
				in.close();
				out.close();
				
			}
			
			
//			Thread.sleep(1000);
		}
				
		
		System.out.println("프로그램 종료");
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

	
}
