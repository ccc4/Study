package io;

import java.io.*;
public class IO05 {	
	public static void main(String[] args) throws IOException {
		char [] msg;
		msg = new char[20];
		
		// InputStreamReader Ŭ����
		// ����Ʈ ��Ʈ�� Ÿ���� ��ü�� �������� �Ű�������
		// �޾Ƶ鿩, �ش� ����Ʈ ��Ʈ���� ���� ��Ʈ������ ��ȯ���ִ� Ŭ����
		// �Ʒ��� ���� �ڵ�� �⺻ �Է¿� ���� ����Ʈ ��Ʈ���� ���� ��Ʈ������
		// �����ϴ� ���� �ڵ�
		InputStreamReader isr = 
				new InputStreamReader(System.in);
		
		System.out.print("�޼����� �Է��ϼ��� : ");
		int size = isr.read(msg);	
		
		System.out.println("�о�� ������ ������ " + size);
		System.out.println();			
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print(msg[i] + " ");
		
		System.out.println();
		System.out.println();
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print((char)msg[i]);
	}
}
