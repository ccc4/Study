package io;
import java.io.*;
import java.net.*;
public class IO07_Example03_Server {
	public static void main(String[] args) throws IOException {
		// 출력할 파일명
		int port = 5050;
		
		ServerSocket serverSocekt = new ServerSocket(port);	
		System.out.println("서버 시작");
		Socket socket = serverSocekt.accept();
		System.out.println("용진씨 접속");
				
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
			System.out.print("메세지 입력 : ");
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







