package thread;
class ThreadH extends Thread {	
	private ThreadH thread;
	public ThreadH(ThreadH thread, String name) {
		super(name);	
		this.thread = thread;
	}
	public void run() {
		if( thread != null ) {
			try {
				thread.join();
			} catch (InterruptedException e1) {			
				e1.printStackTrace();
			}
		}		
		for( int i = 0 ; i < 10 ; i++ ) {
			System.out.printf("%s : %d\n", 
					this.getName(), i);			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {				
				e.printStackTrace();
				return;
			}
		}
	}
}
public class Thread12 {	
	public static void main(String[] args) {		
		System.out.println("프로그램 시작");		
		ThreadH t1 = new ThreadH(null, "ONE");
		ThreadH t2 = new ThreadH(t1, "TWO");
		ThreadH t3 = new ThreadH(t1, "THREE");			
		t1.start();
		t2.start();
		t3.start();			
		try {			
			t1.join();			
			t2.join();			
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		System.out.println("프로그램 종료");		
	}
}







