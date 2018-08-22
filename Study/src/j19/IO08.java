package io;

import java.io.*;
public class IO08 {
	public static void main(String[] args) throws IOException {
		// "C:\\�ڹ����Ϲ�\\AAA";
		String dirName = 
				"C:" + File.separator + "�ڹ����Ϲ�" + 
					File.separator + "AAA";

		String fileName = "JavaFileTest.txt";
		
		File dir = new File(dirName);
				
		if( !dir.exists() )
			dir.mkdirs();
		
		File file = new File(dir, fileName);
				
		if( !file.exists() )
			file.createNewFile();
		
		System.out.printf("file�� �̸� : %s\n", file.getName());
		System.out.printf("file�� ��� : %s\n", file.getAbsolutePath());
		System.out.printf("file�� ũ�� : %s\n", file.length());		
		
		for( int i = 1 ; i <= 8 ; i++ ) {
			File temp = 
					new File( 
						dir.getAbsolutePath() + 
						File.separator + "Day" + i );
			temp = new File( dir, "Day" + i);
			
			if( !temp.exists() )
				temp.mkdirs();
		}
		
		String [] list = dir.list();
		
		for( int i = 0 ; i < list.length ; i++ ) {
			File temp = new File(dir, list[i]);
			
			if( temp.isDirectory() )
				System.out.println(list[i] + 
						" : ���丮( " + 
						temp.list().length + " )");
			
			else if( temp.isFile() )
				System.out.println(list[i] + 
						" : ����( " + temp.length() + " Byte )");				
		}			
	}
}
















