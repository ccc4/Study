package thread;
class ThreadD extends Thread {
	public void run() {
		// �������� �̸��� �������� ������ �����Ǵ� ������
		// Thread-0, 1, 2 ... ���� �̸��� ����մϴ�.
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
