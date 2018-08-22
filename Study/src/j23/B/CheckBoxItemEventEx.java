package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CheckBoxItemEventEx extends JFrame {
	Container contentPane;
	JCheckBox[] fruits = new JCheckBox[3];
	String[] names = { "���", "��", "ü��" };
	JLabel titleLabel;
	JLabel sumLabel;
	int sum = 0;
	
	JPanel centerPanel = new JPanel();

	CheckBoxItemEventEx() {
		setTitle("üũ�ڽ��� ItemEvent  ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
				
		titleLabel = new JLabel("���  100��, �� 500��, ü�� 20000��");
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
		
		sumLabel = new JLabel("���� 0 �� �Դϴ�.");
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

			sumLabel.setText("���� " + sum + "�� �Դϴ�.");
		}
	}

	public static void main(String[] args) {
		new CheckBoxItemEventEx();
	}
}
