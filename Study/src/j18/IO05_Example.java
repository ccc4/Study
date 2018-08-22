import java.io.*;
public class IO05_Example {
	public static void main(String[] args) throws IOException {
		String fileName = "C:\\IO05_Example.txt";
		
		// 파일에 바이트 단위로 입출력할 수 있는 바이트 스트림 객체
		FileOutputStream foutput = new FileOutputStream(fileName);
		FileInputStream finput = new FileInputStream(fileName);
		
		// 파일에 바이트 단위로 출력할 수 있는 foutput을 사용하여
		// 문자로 출력할 수 있는 스트림을 생성
		// (OutputStreamWriter 클래스는 출력 바이트 스트림을
		//  출력 문자 스트림으로 변환해줍니다.)
		OutputStreamWriter writer = new OutputStreamWriter(foutput);
		// 파일로부터 바이트 단위로 입력받을 수 있는 finput을 사용하여
		// 문자를 입력받을 수 있는 스트림을 생성
		InputStreamReader reader = new InputStreamReader(finput);
		
		// 키보드 입력을 위한 바이트 스트림인 System.in 객체를 
		// 문자 스트림으로 변경하는 코드
		InputStreamReader keyborad = 
				new InputStreamReader(System.in);
		
		int input;
		while( true ) {
			System.out.print("문자를 입력하세요 : ");
			input = keyborad.read();
			keyborad.skip(2);
			if( input == 'n' || input == 'N' ) {
				System.out.println("입력 종료");
				break;
			}
			
			writer.write(input);
		}
		writer.close();
		
		System.out.println("파일 내용 출력");
		while( (input = reader.read()) != -1 ) 
			System.out.printf("%c ", (char)input);
	}
}










