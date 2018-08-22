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
			
		// ��Ʈ��ũ�� ��ü�� ����ϱ� ���� ��Ʈ��
		ObjectOutputStream out = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						client.getOutputStream()));			
		out.flush();		
		// ��Ʈ��ũ�κ��� ��ü�� �Է¹ޱ� ���� ��Ʈ��
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
		
		// Ŭ���̾�Ʈ���� ��ü�� ���
		out.writeObject(data);
		out.flush();
		System.out.println("���� ��� �Ϸ�");
		
		// Ŭ���̾�Ʈ�� ������ ��ü�� �Է�
		data = (Data_02)in.readObject();
		System.out.println(data.getMessage());
		
		// ���ҽ� ����
		bis.close();
		in.close();
		out.close();
		client.close();
		server.close();		
	}
}





