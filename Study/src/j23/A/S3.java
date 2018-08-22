import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class S3 extends JFrame {
	Container contentPane;
	
	
	public S3() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		
		ImageIcon a = new ImageIcon("aaa.jpg");
		ImageIcon b = new ImageIcon("bbb.jpg");
		ImageIcon c = new ImageIcon("ccc.jpg");
		
		JButton btn = new JButton("´­·¯ºÁ", a);
		btn.setRolloverIcon(b);
		btn.setPressedIcon(c);
		
		contentPane.add(btn);
		
		
		
		
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new S3();
	}
}
