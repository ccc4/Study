package io;

import java.io.*;
public class IO05 {	
	public static void main(String[] args) throws IOException {
		char [] msg;
		msg = new char[20];
		
		// InputStreamReader 클래스
		// 바이트 스트림 타입의 객체를 생성자의 매개변수로
		// 받아들여, 해당 바이트 스트림을 문자 스트림으로 변환해주는 클래스
		// 아래의 생성 코드는 기본 입력에 대한 바이트 스트림을 문자 스트림으로
		// 변경하는 생성 코드
		InputStreamReader isr = 
				new InputStreamReader(System.in);
		
		System.out.print("메세지를 입력하세요 : ");
		int size = isr.read(msg);	
		
		System.out.println("읽어온 문자의 개수는 " + size);
		System.out.println();			
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print(msg[i] + " ");
		
		System.out.println();
		System.out.println();
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print((char)msg[i]);
	}
}
