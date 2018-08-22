package thread;
//Thread.yield 메소드
//쓰레드의 실행 순서를 양보하는 메소드
//동일한 우선순위를 갖는 쓰레드에게 실행권을 양보할 수 있습니다.
class ThreadJ extends Thread {	
	private int number;
	public ThreadJ(String name, int number) {
		super(name);	
		this.number = number;
	}
	public void run() {
		for( int i = 0 ; i < 30 ; i++ ) {
			System.out.printf("%s : %d\n", 
					this.getName(), i);
			
			if( number == 1 ) {
				System.out.printf(
						"%d 쓰레드 yield 메소드 호출\n",
						this.number);
				Thread.yield();				
			}
		}
	}
}
public class Thread14 {	
	public static void main(String[] args) {		
		ThreadJ [] arr = new ThreadJ[10];
		
		for( int i = 0 ; i < arr.length ; i++ ) {
			arr[i] = new ThreadJ("Number:" + i, i);
		}
		
		arr[1].setPriority(10);
		arr[3].setPriority(10);
		
		for( int i = 0 ; i < arr.length ; i++ )
			arr[i].start();
	}
}









