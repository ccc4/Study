package thread;

//���α׷��� ���� �� ���ÿ� ó���ϰ��� �ϴ� �۾��� ������ ����
//Thread Ŭ������ ����� �� �ֽ��ϴ�.

//Thread
//���α׷� ������ �帧�� �б��� �� �ִ� ����� �����ϴ� Ŭ����
//ex) OS �� ��Ƽ�½�ŷ
//�������
//1. Thread Ŭ������ ��ӹ޴� ���
//1-1. Thread Ŭ������ ��ӹ޾� public void run() �޼ҵ带 �������̵�
//     �մϴ�.
//	      public void run() : 
//        �����尡 �����Ǿ� ������ �۾��� �����ϴ� �޼ҵ�
//	 1-2. �ش� Ŭ������ ��ü�� �����ϰ�, start �޼ҵ带 �����մϴ�.
//		    �������̵��� run �޼ҵ带 ȣ���ϸ�, �����尡 �����Ǵ� ���� �ƴ�
//     �Ϲ� �޼ҵ带 �����մϴ�.

//2. Runnable �������̽��� �����ϴ� ���
class ThreadB_1 extends Thread {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ ) {			
			System.out.println("ThreadB_1 : i = " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
class ThreadB_2 extends Thread {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ ) {
			System.out.println("ThreadB_2 : i = " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
public class Thread02 {	
	public static void main(String[] args) {
		ThreadB_1 t1 = new ThreadB_1();
		ThreadB_2 t2 = new ThreadB_2();		
		// ������ Ŭ������ ��ӹ��� ��ü�� run �޼ҵ带 ȣ���ϸ�
		// �����尡 �������� �ʰ� �޼ҵ尡 ����˴ϴ�.
		t1.run();
		t2.run();		
		for( int i = 0 ; i < 10 ; i++ ) {			
			System.out.println("main : i = " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}







