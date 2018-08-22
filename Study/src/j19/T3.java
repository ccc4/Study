package io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class T3 {
	public static void main(String[] args) throws Exception {
		System.out.println("프로그램 시작");
		
		String dirName = "c:\\자바평일반2";
		String fileName = "data01.dat";
		
		File dir = new File(dirName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(dir, fileName);
		
		ObjectOutputStream oos =
				new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(file)));
		
		oos.writeObject(111);
		oos.writeInt(10);
		oos.writeObject(12.1);
		oos.writeObject("Test");
		
		oos.close();
		
		System.out.println("프로그램 종료");
		
		
		
	}
}
