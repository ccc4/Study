package io;
import java.io.*;
public class IO07_Example02 {
	public static void main(String[] args) throws IOException {
		// ����� ���ϸ�
		String fileName = "C:\\IO07_Example.txt";
		
		// Ű���忡�� �Է¹��� �� �ִ� ���� ����� ��Ʈ�� ��ü
		InputStream inputStream = System.in;
		BufferedReader in = 
				new BufferedReader(
					new InputStreamReader(inputStream));
		
		// ���Ͽ� ����� �� �ִ� ���� ����� ��Ʈ�� ��ü
		OutputStream outputStream = new FileOutputStream(fileName);	
		PrintWriter out = 
				new PrintWriter(		
					new BufferedWriter(
						new OutputStreamWriter(outputStream)));
		
		String input;
		while(true) {
			System.out.print("���ڿ� �Է� : ");
			input = in.readLine();
			
			if( input.equals("exit") )
				break;
			
			out.println(input);
		}
		
		in.close();
		out.close();		
	}
}







