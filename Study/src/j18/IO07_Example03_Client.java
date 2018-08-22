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
		
		// 키보드에서 입력받을 수 있는 버퍼 기반의 스트림 객체
		InputStream inputStream = socket.getInputStream();
		BufferedReader in = 
				new BufferedReader(
					new InputStreamReader(inputStream));
		
		// 파일에 출력할 수 있는 버퍼 기반의 스트림 객체
		OutputStream outputStream = socket.getOutputStream();	
		PrintWriter out = 
				new PrintWriter(		
					new BufferedWriter(
						new OutputStreamWriter(outputStream)), true);
		
		String input;
		while(true) {
			input = in.readLine();			
			System.out.printf("receive : %s\n", input);
			
			System.out.print("메세지 입력 : ");
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







