package thread;

// �����ڿ��� ���� ������ ����
// wait / notify �޼ҵ�
// �ټ����� �����尡 �ϳ��� ���� �ڿ��� ����� �ÿ�
// �����ڿ��� ���ؼ� �����带 �� ���·� ������, �ǻ츱�� �ֽ��ϴ�.
// ����ȭ�� ������������ �ϸ�, wait() �ڵ带 ���� ������� 
// ��ݻ��·� ������ �˴ϴ�.
// ��� ���·� �մ� ������� �ش� ������ü�� ���� notify �޼ҵ带 
// ȣ���� ��߸� �����¸� �������� �� �ֽ��ϴ�.
class Store {
	public static final int MAX_COUNT = 10;	
	private int product;
	
	public Store(int product) {
		this.product = product;
	}
	
	public synchronized void Buy() {		
		if( product >= MAX_COUNT ) {
			System.out.println("��ǰ�� ���� �ǸŸ� ��ٸ�...");
			try {			
				// Buyer �����带 ��ݻ��·� ����.
				this.wait();
				return;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		product++;		
		System.out.printf("�������� ��ǰ�� ������ %d ���� ������Ŵ\n",
				product);
				
		// Seller �����带 Ȱ��ȭ ��Ŵ
		this.notify();

	}
	public synchronized void Sell() {		
		if( product <= 0 ) {
			System.out.println("��ǰ�� ���� ���Ÿ� ��ٸ�...");
			try {
				// Seller �����带 ��ݻ��·� ����.
				this.wait();
				return;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		product--;		
		System.out.printf("�������� ��ǰ�� ������ %d ���� ���ҽ�Ŵ\n", 
				product);		
		
		// Buyer �����带 Ȱ��ȭ ��Ŵ
		this.notify();	
	}
}
class Buyer extends Thread {	
	private Store store;
	
	public Buyer( Store store ) {
		this.store = store;
	}
	
	public void run() {
		while( true ) {
			store.Buy();	
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}

class Seller extends Thread {	
	private Store store;
	
	public Seller( Store store ) {
		this.store = store;
	}
	
	public void run() {
		while( true ) {
			store.Sell();	
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
			
	}
}
public class Thread17 {
	public static void main(String[] args) {
		Store store = new Store(-3);
		
		Buyer buy = new Buyer(store);
		Seller sell = new Seller(store);
		
		//buy.setPriority(Thread.MAX_PRIORITY);
		//sell.setPriority(Thread.MIN_PRIORITY);
		
		buy.start();
		sell.start();
	}
}





