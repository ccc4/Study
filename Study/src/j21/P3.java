package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class P3 extends JFrame {
	public P3() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 200);
		
		GridLayout grid = new GridLayout(4, 2);
		grid.setVgap(5);
		setLayout(grid);
		
		add(new JLabel("이름"));
		add(new JTextField(" "));
		add(new JLabel("학번"));
		add(new JTextField(" "));
		add(new JLabel("학과"));
		add(new JTextField(" "));
		add(new JLabel("과목"));
		add(new JTextField(" "));

		
	}
	public static void main(String[] args) {
		new P3();
	}
}
