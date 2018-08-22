package net.server;

import java.net.*;
import java.io.*;
import net.data.*;

public class Server_07 {
	public static void main(String[] args) throws Exception {
		int port = 5070;
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server Waiting...");
		Socket client = server.accept();
		System.out.println("Client Connected.");				
			
		// 네트워크에 객체를 출력하기 위한 스트림
		ObjectOutputStream out = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						client.getOutputStream()));			
		out.flush();		
		// 네트워크로부터 객체를 입력받기 위한 스트림
		ObjectInputStream in = 
				new ObjectInputStream(
					new BufferedInputStream(
						client.getInputStream()));	
		
		Data_02 data = new Data_02();
		
		File file = new File("D:\\jdk-10.0.1_windows-x64_bin.exe");
		byte [] contents = new byte[(int)file.length()];
		
		data.setFileName("jdk-10.0.1_windows-x64_bin.exe");
		data.setFileContents(contents);
		
		BufferedInputStream bis = 
			new BufferedInputStream(
				new FileInputStream(file));
		bis.read(contents);
		
		// 클라이언트에게 객체를 출력
		out.writeObject(data);
		out.flush();
		System.out.println("파일 출력 완료");
		
		// 클라이언트가 전송한 객체를 입력
		data = (Data_02)in.readObject();
		System.out.println(data.getMessage());
		
		// 리소스 해제
		bis.close();
		in.close();
		out.close();
		client.close();
		server.close();		
	}
}





