package thread;
//java 프로그램의 구동 과정 - ( main Thread !! )
//1. static 필드와 상수 영역의 메모리 로딩
//2. static 영역에서 public static void main 메소드 검색
//3. 검색된 main 메소드를 사용해 Thread 를 생성
//4. main 쓰레드 구동
public class Thread08 {	
	public static void main(String[] args) {
		
		Thread t = Thread.currentThread();
		String name = t.getName();		
		
		System.out.println("현재 구동중인 쓰레드의 이름은 " + name);
	}
}
