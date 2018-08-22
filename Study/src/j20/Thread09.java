package thread;
//Thread.sleep() 메소드
//Thread 를 잠시 중지할 수 있는 명령으로
//메소드의 매개변수는 밀리세컨초를 입력합니다.
//Thread.sleep(1000) -> 1 초 동안 중지하는 sleep 메소드
//인터럽트 Exception 을 처리해 줘야합니다.
class ThreadF extends Thread {
	public ThreadF(String name) {
		super(name);		
	}
	public void run() {
		for( int i = 0 ; i < 10 ; i++ ) {
			System.out.printf("%s : %d\n", this.getName(), i);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
public class Thread09 {	
	public static void main(String[] args) {		
		ThreadF t1 = new ThreadF("Sleep-1");
		ThreadF t2 = new ThreadF("Sleep-2");
		ThreadF t3 = new ThreadF("Sleep-3");
		ThreadF t4 = new ThreadF("Sleep-4");
		ThreadF t5 = new ThreadF("Sleep-5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();		
	}
}












