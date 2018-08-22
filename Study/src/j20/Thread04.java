package thread;

//프로그램의 수행 중 동시에 처리하고자 하는 작업이 생겼을 때는
//Thread 클래스를 사용할 수 있습니다.

//Thread
//프로그램 실행의 흐름을 분기할 수 있는 방법을 제공하는 클래스
//ex) OS 의 멀티태스킹

//구현방법
//1. Thread 클래스를 상속받는 방법

//2. Runnable 인터페이스를 구현하는 방법
//2-1. Runnable 인터페이스를 구현하는 클래스 작성
//2-2. public void run() 오버라이딩 구현
//2-3. 해당 클래스의 A 객체 생성
//2-4. A 객체를 Thread 클래스의 생성자로 전달하여
//		  Thread 객체 생성
//2-5. 생성시킨 쓰레드의 start 메소드 호출!

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
		// Runnable 인테페이스를 구현한 쓰레드 생성
		// 1. Runnable 인터페이스를 구현한 클래스의 객체 생성
		RunnableA r = new RunnableA();
				
		// 2. 1에서 생성된 객체를 사용하여 Thread 객체 생성
		Thread t = new Thread(r);
		// 3. 2에서 생성된 Thread 객체를 사용하여 start 메소드 호출
		t.start();
		
		// 위의 과정을 한 라인으로 작성한 코드
		new Thread( new RunnableB() ).start();
				
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("main : i = " + i);
	}
}







