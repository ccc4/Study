package net.data;

import java.io.Serializable;

public class D1 implements Serializable {
	private int dataType;
	private String message;
	
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
	
	
}
