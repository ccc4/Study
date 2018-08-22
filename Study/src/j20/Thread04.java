package thread;

//���α׷��� ���� �� ���ÿ� ó���ϰ��� �ϴ� �۾��� ������ ����
//Thread Ŭ������ ����� �� �ֽ��ϴ�.

//Thread
//���α׷� ������ �帧�� �б��� �� �ִ� ����� �����ϴ� Ŭ����
//ex) OS �� ��Ƽ�½�ŷ

//�������
//1. Thread Ŭ������ ��ӹ޴� ���

//2. Runnable �������̽��� �����ϴ� ���
//2-1. Runnable �������̽��� �����ϴ� Ŭ���� �ۼ�
//2-2. public void run() �������̵� ����
//2-3. �ش� Ŭ������ A ��ü ����
//2-4. A ��ü�� Thread Ŭ������ �����ڷ� �����Ͽ�
//		  Thread ��ü ����
//2-5. ������Ų �������� start �޼ҵ� ȣ��!

class RunnableA implements Runnable {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("RunnableA : i = " + i);
	}
}
class RunnableB implements Runnable {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("RunnableB : i = " + i);
	}
}
public class Thread04 {	
	public static void main(String[] args) {
		// Runnable �������̽��� ������ ������ ����
		// 1. Runnable �������̽��� ������ Ŭ������ ��ü ����
		RunnableA r = new RunnableA();
				
		// 2. 1���� ������ ��ü�� ����Ͽ� Thread ��ü ����
		Thread t = new Thread(r);
		// 3. 2���� ������ Thread ��ü�� ����Ͽ� start �޼ҵ� ȣ��
		t.start();
		
		// ���� ������ �� �������� �ۼ��� �ڵ�
		new Thread( new RunnableB() ).start();
				
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("main : i = " + i);
	}
}







