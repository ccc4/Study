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
		System.out.println("프로그램 시작");		
		ThreadG t1 = new ThreadG("Number-1");
		ThreadG t2 = new ThreadG("Number-2");
		ThreadG t3 = new ThreadG("Number-3");		
		t1.start();
		t2.start();
		t3.start();		
		try {
			// main 쓰레드는 t1 쓰레드가 종료할 때 까지 기다립니다.
			t1.join();
			// main 쓰레드는 t2 쓰레드가 종료할 때 까지 기다립니다.
			t2.join();
			// main 쓰레드는 t3 쓰레드가 종료할 때 까지 기다립니다.
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		System.out.println("프로그램 종료");		
	}
}







