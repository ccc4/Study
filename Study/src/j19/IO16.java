package io;

import java.io.*;
public class IO16 {	
	public static void main(String[] args) throws Exception {
		System.out.println("프로그램 시작");
		String dirName = "C:\\자바평일반\\DataIOTest";
		String fileName = "Object01.dat";
		
		File dir = new File(dirName);				
		File file = new File(dir, fileName);
		
		ObjectInputStream ois = 
			new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(file)));		
		
		AA a = (AA)ois.readObject();	

		System.out.println(a.num1);
		System.out.println(a.num2);
		
		ois.close();
		
		System.out.println("프로그램 종료");
	}
}








