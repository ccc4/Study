package net.client;

import net.data.*;
import java.io.*;
import java.net.*;

public class C6 {
	public static void main(String[] args) throws Exception {
		String ip = "localhost";
		int port = 5070;
		
		System.out.println("Client Start");
		Socket client = new Socket(ip, port);
		
		System.out.println("1");
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		System.out.println("2");
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
		out.flush();
		System.out.println("3");
		
		D1 data = (D1) in.readObject();
		System.out.println(data.getMessage());
		
		data.setMessage("Client Message : Nice to meet you");
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		
		in.close();
		out.close();
		client.close();
	}
}
