package net.client;

import java.io.IOException;
import java.net.*;

public class c1 {
	
	public static void main(String[] args) {
		Socket client = null;
		try {
			System.out.println("클라이언트 객체 생성");
			client = new Socket("localhost", 5070);
			System.out.println("서버 접속 완료");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(client != null) {
					client.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
