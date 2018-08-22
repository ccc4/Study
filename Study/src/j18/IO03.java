package io;
import java.io.*;
public class IO03 {	
	public static void main(String[] args) 
			throws IOException {
		byte [] msg;
		msg = new byte[20];
		
		System.out.print("메세지를 입력하세요 : ");
		// 기본 입력 버퍼의 내용을 byte 배열에 넣습니다.
		// 매개변수에 있는 byte 배열의 0 번째 인덱스 부터 입력을 받습니다.
		// 만약 버퍼의 내용이 byte 배열의 크기를 벗어난다면
		// 배열의 크기까지만 입력받습니다.
		
		// byte 배열을 매개변수로 받는 read 메소드는
		// 읽어온 byte 의 크기를 리턴합니다.
		int size = System.in.read(msg);
		
		System.out.println("읽어온 byte 의 크기는 " + size);
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print(msg[i] + " ");
		
		System.out.println();
		System.out.println();
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print((char)msg[i]);

	}
}
