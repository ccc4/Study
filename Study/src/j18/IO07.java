package io;
import java.io.*;
public class IO07 {
	public static void main(String[] args) throws IOException {		
		// BufferedReader Ŭ������ ���� �ڵ�
		BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(
								System.in));
		
		System.out.print("������ �Է��ϼ��� : ");
		String msg = br.readLine();		
		int num = Integer.parseInt(msg);
		System.out.printf("�Էµ� ���� : %d\n", num);
		
		System.out.print("�Ǽ��� �Է��ϼ��� : ");
		msg = br.readLine();		
		float f = Float.parseFloat(msg);
		System.out.printf("�Էµ� �Ǽ� : %.2f\n", f);
		
		System.out.print("�Ǽ��� �Է��ϼ��� : ");
		msg = br.readLine();
		double d = Double.parseDouble(msg);
		System.out.printf("�Էµ� �Ǽ� : %.2f\n", d);		
		
		System.out.print("���ڸ� �Է��ϼ��� : ");
		char ch = (char)br.read();
		System.out.printf("�Էµ� ���� : '%c'\n", ch);
		
		// ��Ʈ���� ����� ������ ������ �ݴϴ�.
		br.close();
	}
}







