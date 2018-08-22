package thread;
class ThreadI extends Thread {	
	public ThreadI(String name) {
		super(name);		
	}
	public void run() {
		for( int i = 0 ; i < 50 ; i++ ) {
			System.out.printf("%s : %d\n", 
					this.getName(), i);			
		}
	}
}
public class Thread13 {	
	public static void main(String[] args) {		
		ThreadI t1 = new ThreadI("ONE");
		ThreadI t2 = new ThreadI("TWO");
		ThreadI t3 = new ThreadI("THREE");
		
		// �������� �켱����
		// 1 ~ 10 ������ ���� ����մϴ�.
		// 1�� ���� ���� �켱����
		// 5�� �߰�
		// 10 ���� ���� �켱����
		// �켱������ ���� ���� ������ �󵵼��� �������ϴ�.
		System.out.printf("t1 �� �켱���� : %d\n", 
				t1.getPriority());
		System.out.printf("t2 �� �켱���� : %d\n", 
				t2.getPriority());
		System.out.printf("t3 �� �켱���� : %d\n", 
				t3.getPriority());
		
		// �������� �켱���� ����
		t3.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t1.setPriority(Thread.MAX_PRIORITY);
		
		System.out.printf("t1 �� �켱���� : %d\n", 
				t1.getPriority());
		System.out.printf("t2 �� �켱���� : %d\n", 
				t2.getPriority());
		System.out.printf("t3 �� �켱���� : %d\n", 
				t3.getPriority());
		
		t1.start();
		t2.start();
		t3.start();
	}
}





