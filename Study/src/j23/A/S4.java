import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class S4 extends JFrame {
	Container contentPane;
	
	public S4() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JCheckBox a = new JCheckBox("���");
		JCheckBox b = new JCheckBox("��");
		ImageIcon qqq = new ImageIcon("qqq.jpg");
		JCheckBox c = new JCheckBox("������", qqq);
		ImageIcon www = new ImageIcon("www.jpg");
		c.setSelectedIcon(www);
		c.setBorderPainted(true);
		
		contentPane.add(a);
		contentPane.add(b);
		contentPane.add(c);
		
		
		
		
		
		
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new S4();
	}
}
