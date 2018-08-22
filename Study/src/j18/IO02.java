package io;
import java.io.IOException;
public class IO02 {
	public static void main(String[] args) throws IOException {
		System.out.println("프로그램 시작");

		int num = 0;
		// 엔터키 처리
		// 엔터키는 \r\n 이 조합된 문자로서
		// 바이트 스트림을 사용하여 엔터리킄 처리하려면
		// 2번의 read 메소드가 필요합니다.
		num = System.in.read();	// 1
		System.out.println("읽어온 값 : " + num);
		System.out.println("읽어온 값 : " + (char) num);
		// 스트림에서 매개변수의 바이트만큼을 제거하는 메소드
		// 아래의 코드는 엔터키의 데이터를 제거하게 됩니다.
		System.in.skip(2);
		
		num = System.in.read();	// 3		
		System.out.println("읽어온 값 : " + num);
		System.out.println("읽어온 값 : " + (char) num);
		System.in.skip(2);

		num = System.in.read();	// 5
		System.out.println("읽어온 값 : " + num);
		System.out.println("읽어온 값 : " + (char) num);
		System.in.skip(2);

		System.out.println("프로그램 종료");
	}
}
