package j25.util;

import java.io.*;

public class InputMsgThread extends Thread {
	private BufferedReader in;
	
	private boolean flag = true;
	
	public InputMsgThread(InputStream is) {
		in = new BufferedReader(new InputStreamReader(is));
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		String msg;
		while(flag) {
			try {
				msg = in.readLine();
				if(msg == null || msg.equals("bye")) {
					break;
				}
				
				System.out.printf("input: %s\n", msg);
				
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("종료");
				break;
			}
		}
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("쓰레드 종료");
		System.exit(0);
	}
}
