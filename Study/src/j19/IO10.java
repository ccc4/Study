package io;
import java.io.*;
public class IO10 {	
	public static void main(String[] args) throws Exception {
		String fileName = "ChatLog.txt";		
		
		BufferedReader br = new BufferedReader(
					new InputStreamReader(System.in));
		
		File file = new File(fileName);
		if( !file.exists() )
			file.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(file, true);
		// ���� ��Ʈ���� ������ ��, �߰� ���� ��Ʈ���� �����ϱ�
		// ���� �ڵ�
		//FileOutputStream fos = new FileOutputStream(file, true);
		OutputStreamWriter osw = 
				new OutputStreamWriter(fos);
		BufferedWriter bw = 
				new BufferedWriter(osw);
		
		// PrintWriter ��ü�� ������ ��, autoflush �Ӽ���
		// false �� �����ϸ� ��� ��� �޼ҵ��� ���� ���Ŀ�
		// ��������� flush �޼ҵ尡 ȣ���ؾ߸� ��Ʈ���� ����
		// �����Ͱ� ���޵˴ϴ�.
		// PrintWriter pw = new PrintWriter(bw);
		
		// PrintWriter ��ü�� ������ ��, autoflush �Ӽ���
		// true �� �����ϸ� ��� ��� �޼ҵ��� ���� ���Ŀ�
		// �ڵ����� flush �޼ҵ尡 ����˴ϴ�.
		PrintWriter pw = 
				new PrintWriter(bw, true);
		
		// FileWriter fw = new FileWriter(file, true);		
		// BufferedWriter bw = new BufferedWriter(fw);
		// PrintWriter pw = new PrintWriter(bw, true);
		
		char isCheckExit; 
		String msg;
		do {
			System.out.print("�޼����� �Է��ϼ��� : ");
			msg = br.readLine();			
			
			pw.printf("�Էµ� �޼����� %s �Դϴ�.", msg);
			pw.println();
			pw.println(msg);
			// ���۸� ���� ���
			//pw.flush();
			
			System.out.print("���α׷��� �����Ͻðڽ��ϱ�?(y/n) : ");
			
			isCheckExit = (char)br.read();
			// ����Ű�� �����ϴ� 2���� ���ڸ� �ǳʶٴ� ���
			br.skip(2);
			//isCheckExit = br.readLine().charAt(0);
			
		} while( isCheckExit == 'n' || isCheckExit == 'N' );
		
		br.close();
		pw.close();
		
		System.out.println("���α׷� ����");
	}
}







