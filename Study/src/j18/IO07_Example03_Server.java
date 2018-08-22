package io;
import java.io.*;
import java.net.*;
public class IO07_Example03_Server {
	public static void main(String[] args) throws IOException {
		// ����� ���ϸ�
		int port = 5050;
		
		ServerSocket serverSocekt = new ServerSocket(port);	
		System.out.println("���� ����");
		Socket socket = serverSocekt.accept();
		System.out.println("������ ����");
				
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
			System.out.print("�޼��� �Է� : ");
			input = key.readLine();
			
			if( input.equals("exit") )
				break;
			
			out.println(input);
			
			input = in.readLine();
			
			System.out.printf("receive : %s\n", input);
		}
		
		key.close();
		in.close();
		out.close();		
	}
}







