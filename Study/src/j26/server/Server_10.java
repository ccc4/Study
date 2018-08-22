package net.server;

import java.io.IOException;
// ��Ʈ��ũ�� ���õ� ��Ű�� ����Ʈ
import java.net.*;

public class Server_10 {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try { 
			System.out.println("���� ���� ����");
			server = new ServerSocket(5070);
			
			System.out.println("���� ���� accept ����");
			
			client = server.accept(); 			
			System.out.println("Ŭ���̾�Ʈ ����~!");
			
			InetAddress ia = client.getInetAddress();
			System.out.println("Ŭ���̾�Ʈ ����");
			System.out.printf("Ŭ���̾�Ʈ ȣ��Ʈ �̸� : %s\n", 
					ia.getHostName());
			System.out.printf("Ŭ���̾�Ʈ ȣ��Ʈ ��巹�� : %s\n",
					ia.getHostAddress());
			
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {			
			try {
				if( server != null )
					server.close();
				if( client != null )
					client.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}











