package io;

import java.io.*;

// Serializable ����ȭ�� ���
// Ŭ������ ��� ����ʵ�
// ���������ڴ� ����ȭ�� ���� �����ϴ�.
// final �ʵ嵵 ����ȭ�� ����Դϴ�.
// - ��ü ���� �����ϰ� ��������� ����� 
//   ��� ����ȭ�� ����Դϴ�.
// static �ʵ�� ����ȭ�� ���� �ʽ��ϴ�.!!!
// transient �ʵ�� ����ȭ�� ���� �ʽ��ϴ�.!!!

class B implements Serializable {
	public int num1;
	private int num2;
	protected int num3;
	int num4;
	public final int num5;
	public static int num6;
	// ����ȭ�� ��󿡼� ������ ����ʵ��� ����!!!
	public transient int num7;
	
	public B(int num1, int num2, int num3, int num4, 
			int num5, int num6, int num7) {		
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.num6 = num6;
		this.num7 = num7;
	}
	public void Display() {
		System.out.printf("%d, %d, %d, %d, %d, %d, %d\n",
				num1, num2, num3, num4, num5, num6, num7);
	}	
}
public class IO17 {	
	public static void main(String[] args) throws Exception {
		String dirName = "C:\\�ڹ����Ϲ�\\DataIOTest";
		String fileName = "Object03.dat";
		File dir = new File(dirName);				
		File file = new File(dir, fileName);
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));
		
		B b = new B(10, 20, 30, 40, 50, 60, 70);	
		b.Display();
		
		oos.writeObject(b);		
		oos.close();

			
		B.num6 = 0;
		b.Display();

		ObjectInputStream ois = 
			new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(file)));
		
		B b1 = (B)ois.readObject();
		b1.Display();
		ois.close();
	}
}











