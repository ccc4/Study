package io;
import java.io.IOException;
public class IO02 {
	public static void main(String[] args) throws IOException {
		System.out.println("���α׷� ����");

		int num = 0;
		// ����Ű ó��
		// ����Ű�� \r\n �� ���յ� ���ڷμ�
		// ����Ʈ ��Ʈ���� ����Ͽ� ���͸��� ó���Ϸ���
		// 2���� read �޼ҵ尡 �ʿ��մϴ�.
		num = System.in.read();	// 1
		System.out.println("�о�� �� : " + num);
		System.out.println("�о�� �� : " + (char) num);
		// ��Ʈ������ �Ű������� ����Ʈ��ŭ�� �����ϴ� �޼ҵ�
		// �Ʒ��� �ڵ�� ����Ű�� �����͸� �����ϰ� �˴ϴ�.
		System.in.skip(2);
		
		num = System.in.read();	// 3		
		System.out.println("�о�� �� : " + num);
		System.out.println("�о�� �� : " + (char) num);
		System.in.skip(2);

		num = System.in.read();	// 5
		System.out.println("�о�� �� : " + num);
		System.out.println("�о�� �� : " + (char) num);
		System.in.skip(2);

		System.out.println("���α׷� ����");
	}
}
