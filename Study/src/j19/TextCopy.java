package io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextCopy {
	public static void main(String[] args) throws IOException {
		String srcName = "c:\\windows\\system.ini";
		String dscName = "c:\\tmp\\system.txt";
		
		File sFile = new File(srcName);
		File dFile = new File(dscName);
		
		BufferedReader in = 
				new BufferedReader(
						new FileReader(sFile));
		PrintWriter out =
				new PrintWriter(
						new BufferedWriter(
								new FileWriter(dFile)));
		
		String input;
		while((input = in.readLine()) != null) {
			out.println(input);
		}
		out.close();
		in.close();
	}
}
