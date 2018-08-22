package thread;
class ThreadG extends Thread {	
	public ThreadG(String name) {
		super(name);		
	}
	public void run() {
		for( int i = 0 ; i < 10 ; i++ ) {
			System.out.printf("%s : %d\n", 
					this.getName(), i);			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
public class Thread11 {	
	public static void main(String[] args) {		
		System.out.println("���α׷� ����");		
		ThreadG t1 = new ThreadG("Number-1");
		ThreadG t2 = new ThreadG("Number-2");
		ThreadG t3 = new ThreadG("Number-3");		
		t1.start();
		t2.start();
		t3.start();		
		try {
			// main ������� t1 �����尡 ������ �� ���� ��ٸ��ϴ�.
			t1.join();
			// main ������� t2 �����尡 ������ �� ���� ��ٸ��ϴ�.
			t2.join();
			// main ������� t3 �����尡 ������ �� ���� ��ٸ��ϴ�.
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		System.out.println("���α׷� ����");		
	}
}







