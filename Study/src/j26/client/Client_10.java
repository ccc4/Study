package net.client;

import java.io.IOException;
// ��� ��� ������ ���� ��Ű�� ����Ʈ
import java.net.*;

public class Client_10 {

	public static void main(String[] args) {		
		Socket client = null;		
		try {
			
			System.out.println("Ŭ���̾�Ʈ ��ü ����");			
			client = new Socket("192.168.10.77", 5070);			
			System.out.println("���� ���� �Ϸ�");
			
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









