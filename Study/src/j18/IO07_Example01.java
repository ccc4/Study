package io;
import java.io.*;
public class IO07_Example01 {
	public static void main(String[] args) throws IOException {		
		// IO07.java ������ ������ �о�ͼ�
		// ȭ�鿡 ����ϼ���.		
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







