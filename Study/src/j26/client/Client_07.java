package net.client;

import java.net.*;
import java.io.*;
import net.data.*;

public class Client_07 {
	public static void main(String[] args) throws Exception {		
		String ip = "localhost";
		int port = 5070;
		
		Socket client = new Socket(ip, port);
				
		// 서버로부터 객체를 입력받기 위한 스트림
		ObjectInputStream in = 
				new ObjectInputStream(
					new BufferedInputStream(
						client.getInputStream()));
		// 서버에 객체를 출력하기 위한 스트림
		ObjectOutputStream out = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						client.getOutputStream()));
		out.flush();		
				
		Data_08 data = (Data_08)in.readObject();
		
		File dir = new File("downLoadFiles_C");
		if( !dir.exists() )
			dir.mkdirs();
		File file = new File(dir, data.getFileName());
		BufferedOutputStream bos = 
			new BufferedOutputStream(
				new FileOutputStream(file));
		
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









