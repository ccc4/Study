package io;
import java.io.File;

public class C2 {
	public static void main(String[] args) {
		File dir = new File("c:\\a\\b\\c\\d\\e");
		
		if(!dir.exists()) {
			System.out.println("존재하지 않습니다.");
			
			if(dir.mkdirs()) {
				System.out.println("디렉토리 생성 완료");
			} else {
				System.out.println("디렉토리 생성 실패");
			}
		}
	}
}
