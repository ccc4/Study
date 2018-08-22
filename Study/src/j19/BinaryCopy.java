package io;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BinaryCopy {
	public static void main(String[] args) {
		File src = new File("c:\\windows\\explorer.exe");
		File dst = new File("c:\\tmp\\explorer.bin");
		
		FileInputStream fi = null;
		FileOutputStream fo = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		int c;
		
		try {
			fi = new FileInputStream(src);
			fo = new FileOutputStream(dst);
			in = new BufferedInputStream(fi);
			out = new BufferedOutputStream(fo);
			
			while((c = in.read()) != -1) {
				out.write(c);
			}
			
			out.close();
			in.close();
			fo.close();
			fi.close();
			
		} catch (Exception e) {
			System.out.println("파일 복사 오류");
		}
		
	}
}
