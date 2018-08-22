package io;
import java.io.IOException;

public class IO01 {	
	public static void main(String[] args) throws IOException {			
		System.out.println("프로그램 시작");		
		
		int num = 0;				
		
		num = System.in.read();		
		
		System.out.println("읽어온 값 : " + num);
		System.out.println("읽어온 값 : " + (char)num);	
		
		System.out.println("프로그램 종료");
	}
}







