package io;

import java.io.*;
public class IO13 {	
	public static void main(String[] args) throws Exception {
		System.out.println("���α׷� ����");
		
		String dirName = "C:\\�ڹ����Ϲ�";
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
		
		// �⺻ �����͸� ������ϴ� ObjectOutputStream �� 
		// ObjectInputStream��
		// �����ϴ� ������ �д� ������ �����ؾ� �մϴ�.
		
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
		
		System.out.println("���α׷� ����");
	}
}













