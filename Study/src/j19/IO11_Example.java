package io;

import java.io.*;
public class IO11_Example {	
	
	public static void copyDir(File sDir, File dDir) throws Exception {
		
		
		if( !dDir.exists() )
			dDir.mkdirs();
		
		String [] list = sDir.list();
		
		for( int i = 0 ; i < list.length ; i++ ) {
			File sFile = new File(sDir, list[i]);
			File dFile = new File(dDir, list[i]);
						
			BufferedReader in = 
					new BufferedReader(
							new FileReader(sFile));
			PrintWriter out = 
					new PrintWriter(
							new BufferedWriter(
									new FileWriter(dFile)));
			
			String input;
			while( (input = in.readLine()) != null )
				out.println(input);
			
			in.close();
			out.close();			
		}
	}
	
	public static void main(String[] args) throws Exception {	
		long start = System.currentTimeMillis(); 
		System.out.println("���α׷� ����");

		
		
		// io ��Ű���� �ִ� ��� ������ io2 ���丮�� �����ϼ���
		String dirSrcName = 
				"C:\\dev\\java_se\\re\\JAVA_SE_Day_19\\src\\io";
		String dirDescName = 
				"C:\\dev\\java_se\\re\\JAVA_SE_Day_19\\src\\io2";
		
		File sDir = new File(dirSrcName);
		File dDir = new File(dirDescName);
		
		if( !dDir.exists() )
			dDir.mkdirs();
		
		String [] list = sDir.list();
		
		for( int i = 0 ; i < list.length ; i++ ) {
			File sFile = new File(sDir, list[i]);
			File dFile = new File(dDir, list[i]);
			
			if( sFile.isDirectory() ) {
				copyDir(sFile, dFile);
				continue;
			}
			
			BufferedReader in = 
					new BufferedReader(
							new FileReader(sFile));
			PrintWriter out = 
					new PrintWriter(
							new BufferedWriter(
									new FileWriter(dFile)));
			
			String input;
			while( (input = in.readLine()) != null )
				out.println(input);
			
			in.close();
			out.close();			
		}
		
		
		
		
		System.out.println("���α׷� ����");
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}











