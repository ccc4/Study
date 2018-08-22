package io;

import java.io.*;

// 사용자 정의 클래스의 객체를 입/출력하는 방법
// ObjectInputStream/ObjectOutputStream 클래스를 사용합니다.
// 단, 객체 입출력을 위해서는 Serializable 인터페이스를 구현한 
// 클래스만 가능합니다.

// 직렬화를 구현하지 위해서 Serializable 인터페이스를 구현합니다.
// Serializable 인터페이스는 추상메소드가 없기 때문에 
// 선언만 필요합니다.
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
		System.out.println("프로그램 시작");
		String dirName = "C:\\자바평일반\\DataIOTest";
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
		
		System.out.println("프로그램 종료");
	}
}









