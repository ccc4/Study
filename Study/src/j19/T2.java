package io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T2 {
	public static void main(String[] args) throws Exception {
		String dirSrcName = 
				"C:\\dev\\java_se\\re\\JAVA_SE_Day_19\\src\\io";
		
		File dir1 = new File(dirSrcName);
		String[] list = dir1.list();
		
		
		for(int i=0;i<list.length;i++) {
			File temp = new File(dir1, list[i]);
			
			System.out.println(temp.getPath());
			
			
		}
	}
}
