package j26.data;

import java.io.Serializable;

public class D8 implements Serializable {
	public static final int TYPE_MESSAGE = 1;
	public static final int TYPE_FILE = 2;
	
	private int type;
	private String message;
	private String fileName;
	private byte[] fileContents;
	private String contentText;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.type = TYPE_FILE;
		this.fileName = fileName;
	}
	public byte[] getFileContents() {
		return fileContents;
	}
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.type = TYPE_MESSAGE;
		this.message = message;
	}
	public int getType() {
		return type;
	}
	
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	
	
}
