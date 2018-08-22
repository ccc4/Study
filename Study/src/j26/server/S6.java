package net.server;

import java.net.*;

import javax.swing.JFrame;

import net.data.*;

import java.io.*;

public class S6 {

	
	
	
	
	public static void main(String[] args) throws Exception {
		int port = 5070;
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server Waiting...");
		Socket client = server.accept();
		System.out.println("Client Connected");
		
		System.out.println("1");
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
		out.flush();
		System.out.println("2");
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		System.out.println("3");
		
		D1 data = new D1();
		data.setMessage("Server Message : Hi~");
		
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		data = (D1) in.readObject();
		System.out.println(data.getMessage());
		
		out.close();
		in.close();
		client.close();
		server.close();
	}
}
