package thread;
class ThreadI extends Thread {	
	public ThreadI(String name) {
		super(name);		
	}
	public void run() {
		for( int i = 0 ; i < 50 ; i++ ) {
			System.out.printf("%s : %d\n", 
					this.getName(), i);			
		}
	}
}
public class Thread13 {	
	public static void main(String[] args) {		
		ThreadI t1 = new ThreadI("ONE");
		ThreadI t2 = new ThreadI("TWO");
		ThreadI t3 = new ThreadI("THREE");
		
		// 쓰레드의 우선순위
		// 1 ~ 10 까지의 값을 사용합니다.
		// 1은 가장 작은 우선순위
		// 5는 중간
		// 10 가장 높은 우선순위
		// 우선순위가 높을 수록 실행의 빈도수가 높아집니다.
		System.out.printf("t1 의 우선순위 : %d\n", 
				t1.getPriority());
		System.out.printf("t2 의 우선순위 : %d\n", 
				t2.getPriority());
		System.out.printf("t3 의 우선순위 : %d\n", 
				t3.getPriority());
		
		// 쓰레드의 우선순위 변경
		t3.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t1.setPriority(Thread.MAX_PRIORITY);
		
		System.out.printf("t1 의 우선순위 : %d\n", 
				t1.getPriority());
		System.out.printf("t2 의 우선순위 : %d\n", 
				t2.getPriority());
		System.out.printf("t3 의 우선순위 : %d\n", 
				t3.getPriority());
		
		t1.start();
		t2.start();
		t3.start();
	}
}





