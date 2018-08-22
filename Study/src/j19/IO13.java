package io;

import java.io.*;
public class IO13 {	
	public static void main(String[] args) throws Exception {
		System.out.println("프로그램 시작");
		
		String dirName = "C:\\자바평일반";
		String fileName = "data01.dat";
		
		File dir = new File(dirName);		
		File file = new File(dir, fileName);
		
		/*
		FileInputStream fis = 
				new FileInputStream(file);
		BufferedInputStream bis = 
				new BufferedInputStream(fis);
		ObjectInputStream ois = 
				new ObjectInputStream(bis);
		*/
		ObjectInputStream ois = 
			new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(file)));
		
		// 기본 데이터를 입출력하는 ObjectOutputStream 과 
		// ObjectInputStream은
		// 저장하는 순서와 읽는 순서가 동일해야 합니다.
		
		int i = (Integer)ois.readObject();
		System.out.printf("readInt : %d\n", i);
		
		float f = (Float)ois.readObject();
		System.out.printf("readFloat : %.2f\n", f);
		
		double d = (Double)ois.readObject();
		System.out.printf("readDouble : %.2f\n", d);		
		
		String msg = (String)ois.readObject();
		System.out.printf("readUTF : %s\n", msg);
		
		
		byte b = (Byte)ois.readObject();
		System.out.printf("readByte : %d\n", b);
		
		ois.close();
		
		System.out.println("프로그램 종료");
	}
}













