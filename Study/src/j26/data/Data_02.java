package net.data;

import java.io.*;
import java.util.*;

public class Data_02 implements Serializable {
	private int dataType;
	private String message;
	private String fileName;
	private byte [] fileContents;
	//private ArrayList<Byte> fileContents;
	
	public void setMessage(String message) {
		this.dataType= 1;
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setFileName(String fileName) {
		this.dataType= 2;
		this.fileName = fileName;
	}
	
	public void setFileContents(byte [] fileContents) {		
		this.fileContents = fileContents;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public byte [] getFileContents() {
		return this.fileContents;
	}
	
	public int getFileSize() {
		return this.fileContents.length;
	}
	
	public int getDataType() {
		return this.dataType;
	}
}








