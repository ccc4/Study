package io;
import java.io.*;
// System.in.read 메소드를 사용해서
// 1자리의 정수 두개를 입력받아 합계를 출력하세요
/*
	첫번째 정수를 입력하세요 : 5
	두번째 정수를 입력하세요 : 7
	5 + 7 = 12
*/
public class IO04_Example01 {
	public static void main(String[] args) {		
		int num1 = 0, num2 = 0, sum;
		
		try {
			System.out.print("첫번째 정수를 입력하세요 : ");
			
			// 실제 정수값을 추출하기 위해 문자 0 의 값을 빼줍니다.
			num1 = System.in.read() - '0';
			// 엔터키 값 제거
			// System.in.read();	System.in.read();
			System.in.skip(2);
			
			System.out.print("두번째 정수를 입력하세요 : ");
			// 실제 정수값을 추출하기 위해 문자 0 의 값을 빼줍니다.
			num2 = System.in.read() - '0';
			
			// 엔터키 값 제거
			// System.in.read();	System.in.read();
			System.in.skip(2);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		sum = num1 + num2;
		System.out.printf("%d + %d = %d\n", num1, num2, sum);
	}
}












