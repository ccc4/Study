package net.server;

import java.io.IOException;
import java.net.*;

public class s1 {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try {
			System.out.println("辑滚 家南 积己");
			server = new ServerSocket(5070);
			System.out.println("辑滚 家南 accept 角青");
			client = server.accept();
			System.out.println("努扼捞攫飘 立加");
			
			
			
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
