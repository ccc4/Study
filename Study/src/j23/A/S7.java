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
		
		contentPane.add(new Label("�̸�: "));
		contentPane.add(new JTextField(10));
		contentPane.add(new Label("�а�: "));
		contentPane.add(new JTextField("xxx ���а�"));
		contentPane.add(new Label("�ּ�: "));
		contentPane.add(new JTextField("�����...", 20));
		
		
		
		
		
		
		
		
		setSize(350, 200);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new S7();
	}
}
