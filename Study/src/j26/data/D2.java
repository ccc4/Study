package net.data;

import java.io.Serializable;
import java.util.*;
public class D2 implements Serializable {
	private int dataType;
	private String message;
	private String fileName;
	private byte[] fileContents;
//	private ArrayList<byte> fileContents;
	
	public void setMessage(String message) {
		this.dataType = 1;
		this.message = message;
	}
	
	public int getDataType() {
		return this.dataType;
	}

	public String getMessage() {
		return message;
	}

	public void setFileName(String fileName) {
		this.dataType = 2;
		this.fileName = fileName;
	}

	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getFileContents() {
		return fileContents;
	}
	
	public int getFileSize() {
		return this.fileContents.length;
	}
}
