package thread;
//Thread.yield �޼ҵ�
//�������� ���� ������ �纸�ϴ� �޼ҵ�
//������ �켱������ ���� �����忡�� ������� �纸�� �� �ֽ��ϴ�.
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
						"%d ������ yield �޼ҵ� ȣ��\n",
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









