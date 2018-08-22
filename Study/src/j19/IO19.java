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
		
		String dirName = "C:\\자바평일반\\DataIOTest";
		String fileName = "Object02.dat";
		
		File dir = new File(dirName);				
		File file = new File(dir, fileName);
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));
		
		// 직렬화를 구현한 Point 클래스를 저장하는 컬렉션 객체의 저장
		// 컬렉션 내부의 객체가 직렬화를 구현하고 있지않다면
		// 아래의 코드는 예외가 발생합니다.
		// Serializable, Externalizable
		oos.writeObject(list);		
		oos.close();
		
		System.out.println("프로그램 종료");
	}
}






