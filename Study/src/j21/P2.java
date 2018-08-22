package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class P2 extends JFrame {
	public P2() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 250);
		
		setLayout(new BorderLayout());
		add(new JButton("add"), BorderLayout.NORTH);
		add(new JButton("sub"), BorderLayout.SOUTH);
		add(new JButton("mul"), BorderLayout.EAST);
		add(new JButton("div"), BorderLayout.WEST);
		add(new JButton("Calculate"), BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		new P2();
	}
}
