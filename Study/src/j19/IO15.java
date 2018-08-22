package io;

import java.io.*;

// ����� ���� Ŭ������ ��ü�� ��/����ϴ� ���
// ObjectInputStream/ObjectOutputStream Ŭ������ ����մϴ�.
// ��, ��ü ������� ���ؼ��� Serializable �������̽��� ������ 
// Ŭ������ �����մϴ�.

// ����ȭ�� �������� ���ؼ� Serializable �������̽��� �����մϴ�.
// Serializable �������̽��� �߻�޼ҵ尡 ���� ������ 
// ���� �ʿ��մϴ�.
class AA implements Serializable {	
	public int num1;
	public int num2;
	private transient int num3;
	public int getNum3() {
		return num3;
	}
	public void setNum3(int num3) {
		this.num3 = num3;
	}
}
public class IO15 {	
	public static void main(String[] args) throws Exception {
		System.out.println("���α׷� ����");
		String dirName = "C:\\�ڹ����Ϲ�\\DataIOTest";
		String fileName = "Object01.dat";
		
		File dir = new File(dirName);				
		File file = new File(dir, fileName);
		
		if( !dir.exists() )
			dir.mkdirs();
		
		ObjectOutputStream oos = new 
			ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));

		AA a = new AA(); 
		a.num1 = 797;	a.num2 = 353;
		a.setNum3(555);
		
		oos.writeObject(a);
		oos.close();
		
		System.out.println("���α׷� ����");
	}
}









