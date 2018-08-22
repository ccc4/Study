package net.data;

import java.io.*;
import java.util.*;

public class Data_08 implements Serializable {
	public static final int TYPE_MESSAGE = 1;
	public static final int TYPE_FILE = 2;
	
	private int dataType;
	private String message;
	private String fileName;
	private byte [] fileContents;	
	
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








