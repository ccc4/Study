package io;
import java.io.*;
public class IO07 {
	public static void main(String[] args) throws IOException {		
		// BufferedReader 클래스의 생성 코드
		BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(
								System.in));
		
		System.out.print("정수를 입력하세요 : ");
		String msg = br.readLine();		
		int num = Integer.parseInt(msg);
		System.out.printf("입력된 정수 : %d\n", num);
		
		System.out.print("실수를 입력하세요 : ");
		msg = br.readLine();		
		float f = Float.parseFloat(msg);
		System.out.printf("입력된 실수 : %.2f\n", f);
		
		System.out.print("실수를 입력하세요 : ");
		msg = br.readLine();
		double d = Double.parseDouble(msg);
		System.out.printf("입력된 실수 : %.2f\n", d);		
		
		System.out.print("문자를 입력하세요 : ");
		char ch = (char)br.read();
		System.out.printf("입력된 문자 : '%c'\n", ch);
		
		// 스트림의 사용이 끝나면 해제해 줍니다.
		br.close();
	}
}







