package net.data;
import java.io.*;
// 객체 입출력에 사용되는 클래스는
// 반드시 직렬화를 구현해야만 합니다.
public class Data_01 implements Serializable {
	private int dataType;
	private String message;
	
	public void setMessage(String message) {
		this.dataType= 1;
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int getDataType() {
		return this.dataType;
	}
}
