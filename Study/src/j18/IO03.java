package io;
import java.io.*;
public class IO03 {	
	public static void main(String[] args) 
			throws IOException {
		byte [] msg;
		msg = new byte[20];
		
		System.out.print("�޼����� �Է��ϼ��� : ");
		// �⺻ �Է� ������ ������ byte �迭�� �ֽ��ϴ�.
		// �Ű������� �ִ� byte �迭�� 0 ��° �ε��� ���� �Է��� �޽��ϴ�.
		// ���� ������ ������ byte �迭�� ũ�⸦ ����ٸ�
		// �迭�� ũ������� �Է¹޽��ϴ�.
		
		// byte �迭�� �Ű������� �޴� read �޼ҵ��
		// �о�� byte �� ũ�⸦ �����մϴ�.
		int size = System.in.read(msg);
		
		System.out.println("�о�� byte �� ũ��� " + size);
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print(msg[i] + " ");
		
		System.out.println();
		System.out.println();
		
		for( int i = 0 ; i < msg.length ; i++ )
			System.out.print((char)msg[i]);

	}
}
