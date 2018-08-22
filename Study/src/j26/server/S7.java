package net.server;

import java.net.*;

import javax.swing.JFrame;

import net.data.*;

import java.io.*;

public class S7 {

	
	
	
	
	public static void main(String[] args) throws Exception {
		int port = 5070;
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server Waiting...");
		Socket client = server.accept();
		System.out.println("Client Connected");
		
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
		out.flush();
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		
		D2 data = new D2();
		
		File file = new File("C:\\jdk-10.0.2_windows-x64_bin.exe");
		byte[] contents = new byte[(int) file.length()];
		
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		bis.read(contents);
		
		data.setFileName("jdk-10.0.2_windows-x64_bin.exe");
		data.setFileContents(contents); // 레퍼런스이기 때문에 후에 채워짐
		
		out.writeObject(data);
		out.flush();
		System.out.println("파일 출력 완료");
		
		data = (D2) in.readObject();
		System.out.println(data.getMessage());
		
		bis.close();
		in.close();
		out.close();
		client.close();
		server.close();
	}
}
