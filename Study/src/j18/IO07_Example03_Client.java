package io;
import java.io.*;
import java.net.*;
public class IO07_Example03_Client {
	public static void main(String[] args) throws IOException {
		String ip = "192.168.10.77";
		int port = 5050;		
		
		Socket socket = new Socket(ip, port);	
				
		BufferedReader key = 
				new BufferedReader(
					new InputStreamReader(System.in));
		
		// Ű���忡�� �Է¹��� �� �ִ� ���� ����� ��Ʈ�� ��ü
		InputStream inputStream = socket.getInputStream();
		BufferedReader in = 
				new BufferedReader(
					new InputStreamReader(inputStream));
		
		// ���Ͽ� ����� �� �ִ� ���� ����� ��Ʈ�� ��ü
		OutputStream outputStream = socket.getOutputStream();	
		PrintWriter out = 
				new PrintWriter(		
					new BufferedWriter(
						new OutputStreamWriter(outputStream)), true);
		
		String input;
		while(true) {
			input = in.readLine();			
			System.out.printf("receive : %s\n", input);
			
			System.out.print("�޼��� �Է� : ");
			input = key.readLine();
			
			if( input.equals("exit") )
				break;
			
			out.println(input);			
		}
		
		key.close();
		in.close();
		out.close();		
	}
}







