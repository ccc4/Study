package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class P1 extends JFrame {
	public P1() {
		setTitle("ContentPane°ú JFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 150);
//		setVisible(true);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancle"));
		contentPane.add(new JButton("Ignore"));
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		P1 p1 = new P1();
		
		for(int i=1;i<=10;i++) {
			Thread.sleep(2000);
			
			if(i%2 == 1) {
				p1.setVisible(true);
			} else if(i%2 == 0) {
				p1.setVisible(false);
			}
		}
	}
}
