package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class P4 extends JFrame {
	public P4() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 200);
		setLayout(null);
		
		JLabel la = new JLabel("Hello, Press Buttons!");
		la.setLocation(130, 50);
		la.setSize(200, 20);
		add(la);
		
		for(int i=1;i<=9;i++) {
			JButton b = new JButton(Integer.toString(i));
			b.setLocation(i*15, i*15);
			b.setSize(50, 20);
			add(b);
		}
	}
	public static void main(String[] args) {
		new P4();
	}
}
