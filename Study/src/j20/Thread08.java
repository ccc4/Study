package thread;
//java ���α׷��� ���� ���� - ( main Thread !! )
//1. static �ʵ�� ��� ������ �޸� �ε�
//2. static �������� public static void main �޼ҵ� �˻�
//3. �˻��� main �޼ҵ带 ����� Thread �� ����
//4. main ������ ����
public class Thread08 {	
	public static void main(String[] args) {
		
		Thread t = Thread.currentThread();
		String name = t.getName();		
		
		System.out.println("���� �������� �������� �̸��� " + name);
	}
}
