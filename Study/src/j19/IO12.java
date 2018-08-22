package io;

// ��ü Ÿ���� �����
// ObjectInputStream / ObjectOutputStream
// ��ü�� ����� �� �ִ� ����Ʈ ��Ʈ���� Ŭ����

import java.io.*;
public class IO12 {	
	public static void main(String[] args) throws Exception {
		System.out.println("���α׷� ����");
		
		String dirName = "c:\\�ڹ����Ϲ�";
		String fileName = "data01.dat";
		
		File dir = new File(dirName);
		if( !dir.exists() )
			dir.mkdirs();
		
		File file = new File(dir, fileName);
		
		/*
		FileOutputStream fos = 
				new FileOutputStream(file);
		BufferedOutputStream bos = 
				new BufferedOutputStream(fos);
		ObjectOutputStream oos = 
				new ObjectOutputStream(bos);
		*/
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));

		oos.writeObject(107);
		oos.writeObject(10.119f);
		oos.writeObject(975.175);
		oos.writeObject("Hello ObjectOutputStream");
		oos.writeObject(new Byte((byte)11));
		// ���� File �� ��µǴ� ����
		// Buffer ����� Ŭ������ ����ϸ� 
		// flush �޼ҵ带 ���� ����ϰų�
		// close �޼ҵ带 ����� ����մϴ�.
		oos.close();
		
		System.out.println("���α׷� ����");
	}
}










