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
		// ��Ʈ��ũ�� ��ü�� ����ϱ� ���� ��Ʈ��
		ObjectOutputStream out = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						client.getOutputStream()));		
		System.out.println(2);
		
		out.flush();
		
		// ��Ʈ��ũ�κ��� ��ü�� �Է¹ޱ� ���� ��Ʈ��
		ObjectInputStream in = 
				new ObjectInputStream(
					new BufferedInputStream(
						client.getInputStream()));
		System.out.println(3);
		
		Data_01 data = new Data_01();
		data.setMessage("Server Message : Hi~");
		// Ŭ���̾�Ʈ���� ��ü�� ���
		out.writeObject(data);
		out.flush();
		System.out.println(data.getMessage());
		
		// Ŭ���̾�Ʈ�� ������ ��ü�� �Է�
		data = (Data_01)in.readObject();
		System.out.println(data.getMessage());
		
		// ���ҽ� ����
		in.close();
		out.close();
		client.close();
		server.close();		
	}
}





