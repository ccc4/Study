package io;
import java.io.*;
public class IO06 {
	public static void main(String[] args) throws IOException {		
		// InputStreamReader Ŭ������ ������
		// ����Ʈ ��Ʈ���� ���� ��Ʈ������ �����ϴ� ����!!!
		InputStreamReader isr = 
				new InputStreamReader(System.in);
		// BufferedReader Ŭ������ ������ 
		// ���� ��Ʈ������ ���� ���۸� ����� �о���̴� ����
		BufferedReader br = new BufferedReader(isr);
		
		System.out.print("�޼����� �Է��ϼ��� : ");
		String msg = br.readLine();
		
		System.out.printf("�Էµ� �޼��� : %s\n", msg);
		
		// ��Ʈ���� ����� ������ ������ �ݴϴ�.
		br.close();
	}
}







