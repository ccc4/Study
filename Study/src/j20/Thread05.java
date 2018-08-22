package thread;

//�͸�Ŭ������ ����� �������� �������
public class Thread05 {	
	public static void main(String[] args) {
		
		// Thread Ŭ������ �����ϸ鼭 run �޼ҵ带 �������̵� �ϴ� ���
		new Thread() {
			public void run() {
				for( int i = 0 ; i < 10 ; i++ ) {
					System.out.println(
							"Thread i = " + i);
				}
			}
		}.start();
		
		// Thread Ŭ������ �����ϸ鼭 Runnable �������̽��� 
		// ��ü�� �Ѱ��ִ� ���
		// Runnable �������̽��� �͸�Ŭ������ �����մϴ�.
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





