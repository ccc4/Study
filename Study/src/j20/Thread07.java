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
		ThreadE t1 = new ThreadE("���� ���� ������!!!");
		t1.start();				
		ThreadE t2 = new ThreadE("�ƹ��� 1�� ������");
		t2.start();				
		ThreadE t3 = new ThreadE("���� �ð��� ���� ������");
		t3.start();			
	}
}

