package net.client;

import java.io.IOException;
import java.net.*;

public class C10_test {
	
	public static void main(String[] args) throws UnknownHostException {
//		InetAddress ia = InetAddress.getByName("www.naver.com");
		InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
		for(int i=0;i<iaArr.length;i++) {
			System.out.println(iaArr[i].getHostAddress());
			
		}
				
		
//		Socket client = null;
//		try {
//			System.out.println("Ŭ���̾�Ʈ ��ü ����");
//			client = new Socket("192.168.0.6", 5070);
//			System.out.println("���� ���� �Ϸ�");
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if(client != null) {
//					client.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
	}
}
