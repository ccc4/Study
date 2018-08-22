package thread;

//일반 프로그램은 하나의 실행 흐름을 가지고, 
//프로그램 코드를 실행시키는 과정을 수행합니다.
class ThreadA_1 {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("ThreadA_1 : i = " + i);
	}
}
class ThreadA_2 {
	public void run() {
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("ThreadA_2 : i = " + i);
	}
}
public class Thread01 {	
	public static void main(String[] args) {
		ThreadA_1 t1 = new ThreadA_1();
		ThreadA_2 t2 = new ThreadA_2();		
		t1.run();		
		t2.run();			
		for( int i = 0 ; i < 10 ; i++ )
			System.out.println("main : i = " + i);
	}
}






