package net.data;
import java.io.*;
// ��ü ����¿� ���Ǵ� Ŭ������
// �ݵ�� ����ȭ�� �����ؾ߸� �մϴ�.
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
