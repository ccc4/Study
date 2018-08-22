package net.server;

import java.io.IOException;
// 네트워크에 관련된 패키지 임포트
import java.net.*;

public class Server_10 {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try { 
			System.out.println("서버 소켓 생성");
			server = new ServerSocket(5070);
			
			System.out.println("서버 소켓 accept 실행");
			
			client = server.accept(); 			
			System.out.println("클라이언트 접속~!");
			
			InetAddress ia = client.getInetAddress();
			System.out.println("클라이언트 정보");
			System.out.printf("클라이언트 호스트 이름 : %s\n", 
					ia.getHostName());
			System.out.printf("클라이언트 호스트 어드레스 : %s\n",
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











