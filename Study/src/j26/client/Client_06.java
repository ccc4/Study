package net.client;

import java.net.*;
import java.io.*;
import net.data.*;

public class Client_06 {
	public static void main(String[] args) throws Exception {		
		String ip = "localhost";
		int port = 5070;
		
		Socket client = new Socket(ip, port);
		
		System.out.println(1);
		// 서버로부터 객체를 입력받기 위한 스트림
		ObjectInputStream in = 
				new ObjectInputStream(
					new BufferedInputStream(
						client.getInputStream()));		
		System.out.println(2);
		// 서버에 객체를 출력하기 위한 스트림
		ObjectOutputStream out = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						client.getOutputStream()));
		System.out.println(3);
		
		out.flush();
				
		Data_01 data = (Data_01)in.readObject();
		System.out.println(data.getMessage());
		
	
		
		data.setMessage("Client Message : Nice to meet you~");
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		
		in.close();
		out.close();
		client.close();
	}
}









