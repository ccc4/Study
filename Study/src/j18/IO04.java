package io;
import java.io.*;
public class IO04 {	
	public static void main(String[] args) throws IOException {
		byte [] msg;
		msg = new byte[20];		
		
		System.out.print("메세지를 입력하세요 : ");	
		// read(바이트배열의 레퍼런스, 
		//      입력이 시작될 인덱스, 
		//		받아올 데이터크기);
		int size = System.in.read(msg, 3, 7);
		
		System.out.println("읽어온 byte 의 크기는 " + size);
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print(msg[i] + " ");
		
		System.out.println();
		System.out.println();
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print((char)msg[i]);

	}
}
