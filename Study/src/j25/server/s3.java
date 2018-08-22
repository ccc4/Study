package j25.server;
import java.io.*;
import java.net.*;

import j25.util.InputMsgThread;
public class s3 {
	
	public static void main(String[] args) {
		int port = 5070;
		ServerSocket server = null;
		Socket client = null;
		
		BufferedReader key = null;
//		BufferedReader client_in = null;
		PrintWriter client_out = null;
		
		InputMsgThread inputThread = null;
		try {
			System.out.println("���� ���� ����");
			server = new ServerSocket(port);
			System.out.println("���� ���� accept ����");
			client = server.accept();
			System.out.println("Ŭ���̾�Ʈ ����");
			
			key = new BufferedReader(new InputStreamReader(System.in));
//			client_in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			inputThread = new InputMsgThread(client.getInputStream());
			inputThread.start();
			client_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
			
			String msg;
			do {
				System.out.print("�޼��� �Է�: ");
				msg = key.readLine();
				client_out.println(msg);
				client_out.flush();
				
			} while(!msg.equals("bye"));
//			msg = client_in.readLine();
//			System.out.printf("Client �޼���: %s\n", msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(key != null) key.close();
//				if(client_in != null) client_in.close();
				if(client_out != null) client_out.close();
				
				if(server != null) server.close();
				if(client != null) client.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
