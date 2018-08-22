package net.client;

import java.io.IOException;
// 통신 기능 구현을 위한 패키지 임포트
import java.net.*;

public class Client_10 {

	public static void main(String[] args) {		
		Socket client = null;		
		try {
			
			System.out.println("클라이언트 객체 생성");			
			client = new Socket("192.168.10.77", 5070);			
			System.out.println("서버 접속 완료");
			
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
			try {
				if( client != null )
					client.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}









