package io;
import java.io.*;
// System.in.read �޼ҵ带 ����ؼ�
// 1�ڸ��� ���� �ΰ��� �Է¹޾� �հ踦 ����ϼ���
/*
	ù��° ������ �Է��ϼ��� : 5
	�ι�° ������ �Է��ϼ��� : 7
	5 + 7 = 12
*/
public class IO04_Example01 {
	public static void main(String[] args) {		
		int num1 = 0, num2 = 0, sum;
		
		try {
			System.out.print("ù��° ������ �Է��ϼ��� : ");
			
			// ���� �������� �����ϱ� ���� ���� 0 �� ���� ���ݴϴ�.
			num1 = System.in.read() - '0';
			// ����Ű �� ����
			// System.in.read();	System.in.read();
			System.in.skip(2);
			
			System.out.print("�ι�° ������ �Է��ϼ��� : ");
			// ���� �������� �����ϱ� ���� ���� 0 �� ���� ���ݴϴ�.
			num2 = System.in.read() - '0';
			
			// ����Ű �� ����
			// System.in.read();	System.in.read();
			System.in.skip(2);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		sum = num1 + num2;
		System.out.printf("%d + %d = %d\n", num1, num2, sum);
	}
}












