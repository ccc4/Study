package io;
import java.io.*;
public class IO07_Example02 {
	public static void main(String[] args) throws IOException {
		// 출력할 파일명
		String fileName = "C:\\IO07_Example.txt";
		
		// 키보드에서 입력받을 수 있는 버퍼 기반의 스트림 객체
		InputStream inputStream = System.in;
		BufferedReader in = 
				new BufferedReader(
					new InputStreamReader(inputStream));
		
		// 파일에 출력할 수 있는 버퍼 기반의 스트림 객체
		OutputStream outputStream = new FileOutputStream(fileName);	
		PrintWriter out = 
				new PrintWriter(		
					new BufferedWriter(
						new OutputStreamWriter(outputStream)));
		
		String input;
		while(true) {
			System.out.print("문자열 입력 : ");
			input = in.readLine();
			
			if( input.equals("exit") )
				break;
			
			out.println(input);
		}
		
		in.close();
		out.close();		
	}
}







