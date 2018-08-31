package d0829;

import java.io.Serializable;

public class DataItem implements Serializable{
	public static final int TYPE_JOIN = 0;
	public static final int TYPE_MESSAGE = 1;
	public static final int TYPE_FILE = 2;
	
	private int dataType;
	private String nickname;
	private String message;
	private String fileName;
	private byte[] fileContents;
	
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.dataType = TYPE_MESSAGE;
		this.message = message;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.dataType = TYPE_FILE;
		this.fileName = fileName;
	}
	public byte[] getFileContents() {
		return fileContents;
	}
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	
	
	
	
	
}
