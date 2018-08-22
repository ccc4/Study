package thread;

// 공유자원에 대한 쓰레드 제어
// wait / notify 메소드
// 다수개의 쓰레드가 하나의 공유 자원을 사용할 시에
// 공유자원에 대해서 쓰레드를 블럭 상태로 돌리고, 되살릴수 있습니다.
// 동기화를 전제조건으로 하며, wait() 코드를 만난 쓰레드는 
// 잠금상태로 빠지게 됩니다.
// 잠금 상태로 잇는 쓰레드는 해당 공유객체에 대해 notify 메소드를 
// 호출해 줘야만 블럭상태를 빠져나올 수 있습니다.
class Store {
	public static final int MAX_COUNT = 10;	
	private int product;
	
	public Store(int product) {
		this.product = product;
	}
	
	public synchronized void Buy() {		
		if( product >= MAX_COUNT ) {
			System.out.println("상품이 많아 판매를 기다림...");
			try {			
				// Buyer 쓰레드를 잠금상태로 돌림.
				this.wait();
				return;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		product++;		
		System.out.printf("상점에서 상품의 개수를 %d 개로 증가시킴\n",
				product);
				
		// Seller 쓰레드를 활성화 시킴
		this.notify();

	}
	public synchronized void Sell() {		
		if( product <= 0 ) {
			System.out.println("상품이 적어 구매를 기다림...");
			try {
				// Seller 쓰레드를 잠금상태로 돌림.
				this.wait();
				return;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		product--;		
		System.out.printf("상점에서 상품의 개수를 %d 개로 감소시킴\n", 
				product);		
		
		// Buyer 쓰레드를 활성화 시킴
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





