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
	
	public synchronized void output() throws IOException {
		if(this.messages.size() < LIMIT_COUNT) {
			System.out.println("로그 기록이 적어 WAIT 메소드 호출");
			try {
				this.wait();
				return;
			} catch (Exception e) {
				System.out.println("종료신호 발생");
			}
		}
		
		File file = new File("logs.txt");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		
		while(0 < this.messages.size()) {
			String log = this.messages.remove(0);
			out.println(log);
		}
		out.flush();
		
		System.out.println("로그기록 저장 완료");
	}
}

class Saver extends Thread {
	private boolean isActive = true;
	private LogMessages logmesages;
	
	public Saver(LogMessages logmesages) {
		this.logmesages = logmesages;
	}
	
	public void finish() {
		this.isActive = false;
	}
	
	public void run() {
		while(isActive) {
			try {
				logmesages.output();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("saver 종료");
	}
}

public class T8 {
	public static void main(String[] args) throws IOException {
		
		LogMessages logMessages = new LogMessages();
		
		Saver saver = new Saver(logMessages);
		saver.start();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.print("로그 메세지 : ");
			String log = in.readLine();
			
			if(log.equals("exit")) {
				saver.finish();
				saver.interrupt();
				break;
			}
			
			logMessages.input(log);
		}
		
		in.close();
		System.out.println("프로그램 종료");
	}
}
