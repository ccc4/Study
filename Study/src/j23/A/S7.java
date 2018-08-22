import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class S7 extends JFrame {
	Container contentPane;
	
	public S7() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		contentPane.add(new Label("이름: "));
		contentPane.add(new JTextField(10));
		contentPane.add(new Label("학과: "));
		contentPane.add(new JTextField("xxx 공학과"));
		contentPane.add(new Label("주소: "));
		contentPane.add(new JTextField("서울시...", 20));
		
		
		
		
		
		
		
		
		setSize(350, 200);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new S7();
	}
}
