package thread;
class ThreadD extends Thread {
	public void run() {
		// 쓰레드의 이름을 지정하지 않으면 생성되는 순서로
		// Thread-0, 1, 2 ... 같은 이름을 사용합니다.
		System.out.println(this.getName());
	}
}
public class Thread06 {
	public static void main(String[] args) {			
		ThreadD t = new ThreadD();
		t.start();
		ThreadD t1 = new ThreadD();
		t1.start();
		ThreadD t2 = new ThreadD();
		t2.start();
		ThreadD t3 = new ThreadD();
		t3.start();
	}
}
