package thread;

//�Ϲ� ���α׷��� �ϳ��� ���� �帧�� ������, 
//���α׷� �ڵ带 �����Ű�� ������ �����մϴ�.
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






