package io;
import java.io.*;
// System.in.read �޼ҵ带 Ȱ���� ����ڿ��� �Է¹��� ��,
// �Է¹��� ���� C:\\IO04Example02.out �� ����ϼ���.
// ����ڰ� n �Ǵ� N �� �Է��� ������ ��� �Է¹޽��ϴ�.

// ������� �Է��� ������ C:\\IO04Example02.out ������ ������ 
// ȭ�鿡 ����ϼ���.

public class IO04_Example02 {
	public static void main(String[] args) throws Exception {		
		String fileName = "C:\\IO04Example02.out";
		
		int input = 0;
		
		FileOutputStream fos = new FileOutputStream(fileName);
		// FileInputStream ��ü�� ������ ������
		// �ش� ������ �ݵ�� ������߸� ���ܰ� �߻����� �ʽ��ϴ�.
		FileInputStream fis = new FileInputStream(fileName);
		
		while( true ) {
			System.out.print("���� �Է��ϼ��� : ");
			input = System.in.read();
			// System.in.read();	System.in.read();
			System.in.skip(2);
			
			if( input == 'N' || input == 'n' ) {
				System.out.println("�Է��� �����մϴ�.\n");
				break;
			}			
			fos.write(input);
		}		
		fos.close();
		
		System.out.println("������ ������ ����մϴ�.");
		while( (input = fis.read()) != -1 )
			System.out.print((char)input + " ");
		System.out.println();
		
		fis.close();
	}
}









