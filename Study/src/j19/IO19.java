package io;

import java.util.*;
import java.io.*;

public class IO19 {	
	public static void main(String[] args) throws Exception {
		ArrayList<Point> list = new ArrayList<>();		
		
		Random random = new Random();
	
		for( int i = 500 ; i < 10000 ; i++ )	
			// Math.random() * 500
			list.add(new Point(random.nextInt(i),
					random.nextInt(i)));		
		
		for( int i = 0 ; i < list.size() ; i++ )
			list.get(i).Display();
		
		String dirName = "C:\\�ڹ����Ϲ�\\DataIOTest";
		String fileName = "Object02.dat";
		
		File dir = new File(dirName);				
		File file = new File(dir, fileName);
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));
		
		// ����ȭ�� ������ Point Ŭ������ �����ϴ� �÷��� ��ü�� ����
		// �÷��� ������ ��ü�� ����ȭ�� �����ϰ� �����ʴٸ�
		// �Ʒ��� �ڵ�� ���ܰ� �߻��մϴ�.
		// Serializable, Externalizable
		oos.writeObject(list);		
		oos.close();
		
		System.out.println("���α׷� ����");
	}
}






