package net.client;

import java.io.*;
import java.net.*;

public class c2 {
	
	public static void main(String[] args) {
		Socket client = null;
		int port = 5070;
		
		BufferedReader key = null;
		BufferedReader server_in = null;
		PrintWriter server_out = null;
		
		
		try {
			System.out.println("클라이언트 객체 생성");
			client = new Socket("localhost", port);
			System.out.println("서버 접속 완료");
			
			key = new BufferedReader(new InputStreamReader(System.in));
			server_in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			server_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
			
			String msg = server_in.readLine();
			System.out.printf("Server 메세지: %s\n", msg);
			System.out.print("메세지 입력: ");
			msg = key.readLine();
			server_out.println(msg);
			server_out.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(key != null) key.close();
				if(server_in != null) server_in.close();
				if(server_out != null) server_out.close();
				
				if(client != null) client.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
