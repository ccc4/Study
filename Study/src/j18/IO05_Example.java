import java.io.*;
public class IO05_Example {
	public static void main(String[] args) throws IOException {
		String fileName = "C:\\IO05_Example.txt";
		
		// ���Ͽ� ����Ʈ ������ ������� �� �ִ� ����Ʈ ��Ʈ�� ��ü
		FileOutputStream foutput = new FileOutputStream(fileName);
		FileInputStream finput = new FileInputStream(fileName);
		
		// ���Ͽ� ����Ʈ ������ ����� �� �ִ� foutput�� ����Ͽ�
		// ���ڷ� ����� �� �ִ� ��Ʈ���� ����
		// (OutputStreamWriter Ŭ������ ��� ����Ʈ ��Ʈ����
		//  ��� ���� ��Ʈ������ ��ȯ���ݴϴ�.)
		OutputStreamWriter writer = new OutputStreamWriter(foutput);
		// ���Ϸκ��� ����Ʈ ������ �Է¹��� �� �ִ� finput�� ����Ͽ�
		// ���ڸ� �Է¹��� �� �ִ� ��Ʈ���� ����
		InputStreamReader reader = new InputStreamReader(finput);
		
		// Ű���� �Է��� ���� ����Ʈ ��Ʈ���� System.in ��ü�� 
		// ���� ��Ʈ������ �����ϴ� �ڵ�
		InputStreamReader keyborad = 
				new InputStreamReader(System.in);
		
		int input;
		while( true ) {
			System.out.print("���ڸ� �Է��ϼ��� : ");
			input = keyborad.read();
			keyborad.skip(2);
			if( input == 'n' || input == 'N' ) {
				System.out.println("�Է� ����");
				break;
			}
			
			writer.write(input);
		}
		writer.close();
		
		System.out.println("���� ���� ���");
		while( (input = reader.read()) != -1 ) 
			System.out.printf("%c ", (char)input);
	}
}










