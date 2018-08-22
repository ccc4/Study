package io;
import java.io.*;
public class IO06 {
	public static void main(String[] args) throws IOException {		
		// InputStreamReader 클래스의 역할은
		// 바이트 스트림을 문자 스트림으로 변경하는 역할!!!
		InputStreamReader isr = 
				new InputStreamReader(System.in);
		// BufferedReader 클래스의 역할은 
		// 문자 스트림으로 부터 버퍼를 사용해 읽어들이는 역할
		BufferedReader br = new BufferedReader(isr);
		
		System.out.print("메세지를 입력하세요 : ");
		String msg = br.readLine();
		
		System.out.printf("입력된 메세지 : %s\n", msg);
		
		// 스트림의 사용이 끝나면 해제해 줍니다.
		br.close();
	}
}







