package io;
import java.io.File;

public class C2 {
	public static void main(String[] args) {
		File dir = new File("c:\\a\\b\\c\\d\\e");
		
		if(!dir.exists()) {
			System.out.println("�������� �ʽ��ϴ�.");
			
			if(dir.mkdirs()) {
				System.out.println("���丮 ���� �Ϸ�");
			} else {
				System.out.println("���丮 ���� ����");
			}
		}
	}
}
