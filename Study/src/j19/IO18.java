package io;
import java.io.*;
// Externalizable ����ȭ
// Ŭ������ ����ȭ �� ���ִ� �������̽���
// 2���� �߻�޼ҵ带 �����ϰ� �ֽ��ϴ�.
// read / write External �޼ҵ带 
// ����Ͽ� ����ʵ� �� ������ �ʵ常�� ����ȭ �� �� �ֽ��ϴ�.
// ������!!!
// Externalizable �������̽��� �����ϴ� Ŭ������ 
// �ݵ�� ����Ʈ �����ڸ� �ۼ��ؾ� �մϴ�.
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
		String dirName = "C:\\�ڹ����Ϲ�\\DataIOTest";
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











