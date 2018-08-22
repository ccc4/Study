package io;
import java.io.*;
public class IO07_Example01 {
	public static void main(String[] args) throws IOException {		
		// IO07.java 파일의 내용을 읽어와서
		// 화면에 출력하세요.		
		String fileName = 
		"C:\\dev\\java_se\\sources\\Java_Se_Day18\\src\\io\\IO07.java";
		
		BufferedReader br = 
			new BufferedReader(
				new InputStreamReader(
					new FileInputStream(fileName)));
		
		String input;
		
		while( (input = br.readLine()) != null )
			System.out.println(input);
		
		br.close();		
	}
}







