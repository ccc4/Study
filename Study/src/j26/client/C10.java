package net.client;

import java.io.IOException;
import java.net.*;

public class C10 {
	
	public static void main(String[] args) {
		Socket client = null;
		try {
			System.out.println("Ŭ���̾�Ʈ ��ü ����");
			client = new Socket("192.168.0.6", 5070);
			System.out.println("���� ���� �Ϸ�");
			
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
