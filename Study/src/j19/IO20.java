package io;

import java.util.*;
import java.io.*;

public class IO20 {	
	public static void main(String[] args) throws Exception {
		ArrayList<Point> list;
		
		String dirName = "C:\\�ڹ����Ϲ�\\DataIOTest";
		String fileName = "Object02.dat";
		
		File dir = new File(dirName);		
		File file = new File(dir, fileName);		

		ObjectInputStream ois = 
			new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(file)));	

		list = (ArrayList<Point>)ois.readObject();
		ois.close();
		
		for( int i = 0 ; i < list.size() ; i++ )
			list.get(i).Display();		
		
		System.out.println("���α׷� ����");

	}
}

