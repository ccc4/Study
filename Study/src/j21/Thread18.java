package thread;
import java.util.*;
import java.io.*;
class LogMessages {
	private static final int LIMIT_COUNT = 5;	
	private ArrayList<String> messages;
	
	public LogMessages() {
		this.messages = new ArrayList<>();
	}
	
	public synchronized void input(String log) {		
		this.messages.add(log);
		this.notify();
	}
	public synchronized void output() {		
		if( this.messages.size() < LIMIT_COUNT ) {
			System.out.println("�α� ����� ���� WAIT �޼ҵ� ȣ��");
			try {
				this.wait();
				return;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		File file = new File("logs.txt");
		try(PrintWriter out = 
					new PrintWriter(
						new BufferedWriter(
							new FileWriter(file, true)))) {
			
			for( int i = 0, limit = this.messages.size() ; 
					i < limit ; i++ ) {
				String log = this.messages.remove(0);				
				out.println(log);
			}
			
			out.flush();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		System.out.println("�α� ��� ���� �Ϸ�");
	}
}
class Saver extends Thread {	
	private LogMessages logMessages;
	
	public Saver( LogMessages logMessages ) {
		this.logMessages = logMessages;
	}
	
	public void run() {
		while( true ) {
			logMessages.output();
		}
	}
}
public class Thread18 {
	public static void main(String[] args) throws Exception {
		LogMessages logMessages = new LogMessages();		
		Saver saver = new Saver(logMessages);
		saver.start();
		
		BufferedReader in = 
			new BufferedReader(
				new InputStreamReader(System.in));
		
		while(true) {
			System.out.print("�α� �޼��� : ");
			String log = in.readLine();
			
			if( log.equals("exit") )
				break;
			
			logMessages.input(log);
		}
		
		in.close();
		System.out.println("���α׷� ����");
	}
}

















