package thread;

//익명클래스를 사용한 쓰레드의 생성방법
public class Thread05 {	
	public static void main(String[] args) {
		
		// Thread 클래스를 생성하면서 run 메소드를 오버라이딩 하는 방법
		new Thread() {
			public void run() {
				for( int i = 0 ; i < 10 ; i++ ) {
					System.out.println(
							"Thread i = " + i);
				}
			}
		}.start();
		
		// Thread 클래스를 생성하면서 Runnable 인터페이스의 
		// 객체를 넘겨주는 방법
		// Runnable 인터페이스는 익명클래스로 구현합니다.
		new Thread( new Runnable() {
			public void run() {
				for( int i = 0 ; i < 10 ; i++ ) {
					System.out.println(
							"Runnable i = " + i);					
				}
			}
		}).start();

	}

}





