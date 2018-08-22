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
		// 파일 스트림을 생성할 때, 추가 모드로 스트림을 생성하기
		// 위한 코드
		//FileOutputStream fos = new FileOutputStream(file, true);
		OutputStreamWriter osw = 
				new OutputStreamWriter(fos);
		BufferedWriter bw = 
				new BufferedWriter(osw);
		
		// PrintWriter 객체를 생성할 때, autoflush 속성을
		// false 로 지정하면 모든 출력 메소드의 실행 이후에
		// 명시적으로 flush 메소드가 호출해야만 스트림을 통해
		// 데이터가 전달됩니다.
		// PrintWriter pw = new PrintWriter(bw);
		
		// PrintWriter 객체를 생성할 때, autoflush 속성을
		// true 로 지정하면 모든 출력 메소드의 실행 이후에
		// 자동으로 flush 메소드가 실행됩니다.
		PrintWriter pw = 
				new PrintWriter(bw, true);
		
		// FileWriter fw = new FileWriter(file, true);		
		// BufferedWriter bw = new BufferedWriter(fw);
		// PrintWriter pw = new PrintWriter(bw, true);
		
		char isCheckExit; 
		String msg;
		do {
			System.out.print("메세지를 입력하세요 : ");
			msg = br.readLine();			
			
			pw.printf("입력된 메세지는 %s 입니다.", msg);
			pw.println();
			pw.println(msg);
			// 버퍼를 비우는 명령
			//pw.flush();
			
			System.out.print("프로그램을 종료하시겠습니까?(y/n) : ");
			
			isCheckExit = (char)br.read();
			// 엔터키를 구성하는 2개의 문자를 건너뛰는 명령
			br.skip(2);
			//isCheckExit = br.readLine().charAt(0);
			
		} while( isCheckExit == 'n' || isCheckExit == 'N' );
		
		br.close();
		pw.close();
		
		System.out.println("프로그램 종료");
	}
}







