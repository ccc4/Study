package net.server;

import java.net.*;
import java.io.*;
import net.data.*;

public class Server_06 {
	public static void main(String[] args) throws Exception {
		int port = 5070;
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server Waiting...");
		Socket client = server.accept();
		System.out.println("Client Connected.");
				
		System.out.println(1);		
		// 네트워크에 객체를 출력하기 위한 스트림
		ObjectOutputStream out = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						client.getOutputStream()));		
		System.out.println(2);
		
		out.flush();
		
		// 네트워크로부터 객체를 입력받기 위한 스트림
		ObjectInputStream in = 
				new ObjectInputStream(
					new BufferedInputStream(
						client.getInputStream()));
		System.out.println(3);
		
		Data_01 data = new Data_01();
		data.setMessage("Server Message : Hi~");
		// 클라이언트에게 객체를 출력
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		
		// 클라이언트가 전송한 객체를 입력
		data = (Data_01)in.readObject();
		System.out.println(data.getMessage());
		
		// 리소스 해제
		in.close();
		out.close();
		client.close();
		server.close();		
	}
}





