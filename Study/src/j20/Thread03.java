package thread;
class ThreadC_1 extends Thread {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ ) {
			System.out.println("ThreadC_1 : i = " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
class ThreadC_2 extends Thread {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ ) {
			System.out.println("ThreadC_2 : i = " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
public class Thread03 {	
	public static void main(String[] args) {
		ThreadC_1 t1 = new ThreadC_1();
		ThreadC_2 t2 = new ThreadC_2();	
		
		// Thread 클래스를 상속받은 클래스의 객체를 생성한 후
		// start 메소드를 호출하면, 해당 객체는 쓰레드로 
		// 동작하게 됩니다.
		t1.start();
		t2.start();
		
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







