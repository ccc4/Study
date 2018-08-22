package io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class T4 {
	public static void main(String[] args) throws Exception {
		System.out.println("프로그램 시작");
		
		String dirName = "c:\\자바평일반2";
		String fileName = "data01.dat";
		
		File dir = new File(dirName);
		File file = new File(dir, fileName);
		
		ObjectInputStream ois = 
				new ObjectInputStream(
						new BufferedInputStream(
								new FileInputStream(file)));
		
		int i = (Integer)ois.readObject();
		System.out.printf("readInt: %d\n", i);
		int i2 = ois.readInt();
		System.out.printf("readInt: %d\n", i2);
		
		ois.close();
		System.out.println("프로그램 종료");
				
	}
}
