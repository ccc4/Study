package io;
import java.io.*;
// Externalizable 직렬화
// 클래스를 직렬화 할 수있는 인터페이스로
// 2개의 추상메소드를 포함하고 있습니다.
// read / write External 메소드를 
// 사용하여 멤버필드 중 선별된 필드만을 직렬화 할 수 있습니다.
// 주의점!!!
// Externalizable 인터페이스를 구현하는 클래스는 
// 반드시 디폴트 생성자를 작성해야 합니다.
class D implements Externalizable {
	public int n1;
	public int n2;
	public int n3;
	public D(){}
	public D(int n1, int n2, int n3) {		
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
	}
	public void writeExternal(ObjectOutput out) 
			throws IOException {
		System.out.println("D.writeExternal");		
		out.writeInt(n1 * 2);
		out.writeInt(n2 * 2);
		out.writeInt(n3 * 2);
	}
	public void readExternal(ObjectInput in) 
			throws IOException,
			ClassNotFoundException {
		System.out.println("D.readExternal");
		n1 = in.readInt() / 2;
		n2 = in.readInt() / 2;
//		n3 = in.readInt() / 2;
	}
	public void Display() {
		System.out.printf("%d, %d, %d\n", n1, n2, n3);
	}		
}
public class IO18 {	
	public static void main(String[] args) throws Exception {
		String dirName = "C:\\자바평일반\\DataIOTest";
		String fileName = "Object05.dat";
		File dir = new File(dirName);				
		File file = new File(dir, fileName);
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));
		
		D d = new D(10, 20, 30);		
		oos.writeObject(d);
		d.Display();		
		oos.close();		

		ObjectInputStream ois = 
			new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(file)));
		
		D d1 = (D)ois.readObject();
		d1.Display();
		
		ois.close();
	}
}











