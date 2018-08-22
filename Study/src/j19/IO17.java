package io;

import java.io.*;

// Serializable 직렬화의 대상
// 클래스의 모든 멤버필드
// 접근지정자는 직렬화와 관계 없습니다.
// final 필드도 직렬화의 대상입니다.
// - 객체 마다 고유하게 만들어지는 멤버는 
//   모두 직렬화의 대상입니다.
// static 필드는 직렬화가 되지 않습니다.!!!
// transient 필드는 직렬화가 되지 않습니다.!!!

class B implements Serializable {
	public int num1;
	private int num2;
	protected int num3;
	int num4;
	public final int num5;
	public static int num6;
	// 직렬화의 대상에서 빠지는 멤버필드의 선언!!!
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
		String dirName = "C:\\자바평일반\\DataIOTest";
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











