package thread;

import java.util.Random;
import java.io.*;

class ThreadGame extends Thread {	
	public ThreadGame(String name) {
		super(name);		
	}
	public void run() {
		Random r = new Random();
		for( int i = 0 ; i < 10 ; i++ ) {
			try {
				Thread.sleep(r.nextInt(3000));
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}		
		System.out.println(this.getName() + "님 도착!!!");
	}
}

public class Thread10 {	
	public static void main(String[] args) throws Exception {		
		ThreadGame [] arr;
		
		BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(System.in));
		
		System.out.print("참가 인원수를 입력하세요 : ");
		arr = new ThreadGame[ 
		            Integer.parseInt(br.readLine()) ];
		
		for( int i = 0 ; i < arr.length ; i++ ) {
			System.out.printf(
				"%d 번째 참가자의 이름을 입력하세요 : ", i+1);
			arr[i] = new ThreadGame(br.readLine());
		}
		
		for( int i = 0 ; i < arr.length ; i++ )
			arr[i].start();		
		
		for( int i = 0 ; i < arr.length ; i++ )
			arr[i].join();
		
		System.out.println("프로그램 종료!!!");
	}
}












