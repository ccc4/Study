package thread;

//프로그램의 수행 중 동시에 처리하고자 하는 작업이 생겼을 때는
//Thread 클래스를 사용할 수 있습니다.

//Thread
//프로그램 실행의 흐름을 분기할 수 있는 방법을 제공하는 클래스
//ex) OS 의 멀티태스킹
//구현방법
//1. Thread 클래스를 상속받는 방법
//1-1. Thread 클래스를 상속받아 public void run() 메소드를 오버라이딩
//     합니다.
//	      public void run() : 
//        쓰레드가 생성되어 수행할 작업을 정의하는 메소드
//	 1-2. 해당 클래스의 객체를 생성하고, start 메소드를 실행합니다.
//		    오버라이딩한 run 메소드를 호출하면, 쓰레드가 생성되는 것이 아닌
//     일반 메소드를 수행합니다.

//2. Runnable 인터페이스를 구현하는 방법
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
		// 쓰레드 클래스를 상속받은 객체의 run 메소드를 호출하면
		// 쓰레드가 생성되지 않고 메소드가 실행됩니다.
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







