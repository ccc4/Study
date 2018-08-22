package net.client;

import java.net.*;
import java.io.*;
import net.data.*;

public class Client_07 {
	public static void main(String[] args) throws Exception {		
		String ip = "localhost";
		int port = 5070;
		
		Socket client = new Socket(ip, port);
				
		// �����κ��� ��ü�� �Է¹ޱ� ���� ��Ʈ��
		ObjectInputStream in = 
				new ObjectInputStream(
					new BufferedInputStream(
						client.getInputStream()));
		// ������ ��ü�� ����ϱ� ���� ��Ʈ��
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
		
		System.out.println("���� ���� �Ϸ�");
		
		data.setMessage("Client Message : ���� ���� �Ϸ�");
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		
		bos.close();
		in.close();
		out.close();
		client.close();
	}
}









