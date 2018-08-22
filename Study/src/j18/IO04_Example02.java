package io;
import java.io.*;
// System.in.read 메소드를 활용해 사용자에게 입력받은 후,
// 입력받은 값은 C:\\IO04Example02.out 에 출력하세요.
// 사용자가 n 또는 N 을 입력할 때까지 계속 입력받습니다.

// 사용자의 입력이 끝나면 C:\\IO04Example02.out 파일의 내용을 
// 화면에 출력하세요.

public class IO04_Example02 {
	public static void main(String[] args) throws Exception {		
		String fileName = "C:\\IO04Example02.out";
		
		int input = 0;
		
		FileOutputStream fos = new FileOutputStream(fileName);
		// FileInputStream 객체를 생성할 때에는
		// 해당 파일이 반드시 존재햐야만 예외가 발생하지 않습니다.
		FileInputStream fis = new FileInputStream(fileName);
		
		while( true ) {
			System.out.print("값을 입력하세요 : ");
			input = System.in.read();
			// System.in.read();	System.in.read();
			System.in.skip(2);
			
			if( input == 'N' || input == 'n' ) {
				System.out.println("입력을 종료합니다.\n");
				break;
			}			
			fos.write(input);
		}		
		fos.close();
		
		System.out.println("파일의 내용을 출력합니다.");
		while( (input = fis.read()) != -1 )
			System.out.print((char)input + " ");
		System.out.println();
		
		fis.close();
	}
}









