package io;
import java.io.File;
import java.io.IOException;

public class T1 {
	public static void main(String[] args) throws IOException {
		String dirName = "C: " + File.separator + "자바평일반" + File.separator + "AAA";
		
		String fileName = "JavaFileTest.txt";
		
		File dir = new File(dirName);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(dir, fileName);
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		System.out.printf("file의 이름 : %s\n", file.getName());
		System.out.printf("file의 경로 : %s\n", file.getAbsolutePath());
		System.out.printf("file의 크기 : %s\n", file.length());
		
		for(int i=1;i<=8;i++) {
			File temp = new File(dir, "Day" + 1);
			if(!temp.exists()) temp.mkdirs();
		}
		
		String[] list = dir.list();
		
		for(int i=0;i<list.length;i++) {
			File temp = new File(dir, list[i]);
		}
	}
}
