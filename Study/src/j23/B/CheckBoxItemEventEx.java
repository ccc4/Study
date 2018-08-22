package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CheckBoxItemEventEx extends JFrame {
	Container contentPane;
	JCheckBox[] fruits = new JCheckBox[3];
	String[] names = { "사과", "배", "체리" };
	JLabel titleLabel;
	JLabel sumLabel;
	int sum = 0;
	
	JPanel centerPanel = new JPanel();

	CheckBoxItemEventEx() {
		setTitle("체크박스와 ItemEvent  예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
				
		titleLabel = new JLabel("사과  100원, 배 500원, 체리 20000원");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		centerPanel.setLayout(new GridLayout(1, fruits.length));
		for (int i = 0; i < fruits.length; i++) {
			fruits[i] = new JCheckBox(names[i]);
			fruits[i].setBorderPainted(true);
			centerPanel.add(fruits[i]);
			fruits[i].addItemListener(new MyItemListener());
		}		
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		sumLabel = new JLabel("현재 0 원 입니다.");
		sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(sumLabel, BorderLayout.SOUTH);
		
		setSize(250, 200);
		setVisible(true);
	}

	class MyItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			int selected = 1;
			if (e.getStateChange() == ItemEvent.SELECTED)
				selected = 1;
			else
				selected = -1;
			if (e.getItem() == fruits[0])
				sum = sum + selected * 100;
			else if (e.getItem() == fruits[1])
				sum = sum + selected * 500;
			else
				sum = sum + selected * 20000;

			sumLabel.setText("현재 " + sum + "원 입니다.");
		}
	}

	public static void main(String[] args) {
		new CheckBoxItemEventEx();
	}
}
