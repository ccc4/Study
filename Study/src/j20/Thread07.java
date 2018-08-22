package thread;
class ThreadE extends Thread {
	public ThreadE( String name ) {
		super(name);
	}
	public void run() {		
		System.out.println(this.getName());
	}
}
public class Thread07 {
	public static void main(String[] args) {		
		ThreadE t1 = new ThreadE("내가 만든 쓰레드!!!");
		t1.start();				
		ThreadE t2 = new ThreadE("아무개 1번 쓰레드");
		t2.start();				
		ThreadE t3 = new ThreadE("수업 시간에 만든 쓰레드");
		t3.start();			
	}
}

