package net.server;

import java.io.IOException;
import java.net.*;

public class s1 {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try {
			System.out.println("���� ���� ����");
			server = new ServerSocket(5070);
			System.out.println("���� ���� accept ����");
			client = server.accept();
			System.out.println("Ŭ���̾�Ʈ ����");
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(server != null) {
					server.close();
				}
				if(client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
