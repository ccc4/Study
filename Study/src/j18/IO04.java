package io;
import java.io.*;
public class IO04 {	
	public static void main(String[] args) throws IOException {
		byte [] msg;
		msg = new byte[20];		
		
		System.out.print("�޼����� �Է��ϼ��� : ");	
		// read(����Ʈ�迭�� ���۷���, 
		//      �Է��� ���۵� �ε���, 
		//		�޾ƿ� ������ũ��);
		int size = System.in.read(msg, 3, 7);
		
		System.out.println("�о�� byte �� ũ��� " + size);
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print(msg[i] + " ");
		
		System.out.println();
		System.out.println();
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print((char)msg[i]);

	}
}
