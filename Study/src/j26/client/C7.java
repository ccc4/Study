package net.client;

import net.data.*;
import java.io.*;
import java.net.*;

public class C7 {
	public static void main(String[] args) throws Exception {
		String ip = "localhost";
		int port = 5070;
		
		System.out.println("Client Start");
		Socket client = new Socket(ip, port);
		
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
		out.flush();
		
		D2 data = (D2) in.readObject();
		
		File dir = new File("downLoadFiles_C");
		if(!dir.exists()) dir.mkdirs();
		File file = new File(dir, data.getFileName());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		
		bos.write(data.getFileContents());
		bos.flush();
		
		System.out.println("파일 저장 완료");
		
		
		
		data.setMessage("Client Message : 파일 저장 완료");
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		
		bos.close();
		in.close();
		out.close();
		client.close();
	}
}
