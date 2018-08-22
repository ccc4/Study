package io;

// 객체 타입의 입출력
// ObjectInputStream / ObjectOutputStream
// 객체를 출력할 수 있는 바이트 스트림의 클래스

import java.io.*;
public class IO12 {	
	public static void main(String[] args) throws Exception {
		System.out.println("프로그램 시작");
		
		String dirName = "c:\\자바평일반";
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
		// 실제 File 에 출력되는 시점
		// Buffer 기반의 클래스를 사용하면 
		// flush 메소드를 통해 출력하거나
		// close 메소드를 사용해 출력합니다.
		oos.close();
		
		System.out.println("프로그램 종료");
	}
}










