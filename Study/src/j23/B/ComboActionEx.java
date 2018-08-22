package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboActionEx extends JFrame {
	Container contentPane;
	String[] fruits = { "apple", "banana", "kiwi", "mango" };
	ImageIcon[] images = { new ImageIcon("images/apple.jpg"), new ImageIcon("images/banana.jpg"),
			new ImageIcon("images/kiwi.png"), new ImageIcon("images/mango.jpg") };
	JLabel imgLabel = new JLabel(images[0]);

	ComboActionEx() {
		setTitle("리스트 만들기  예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JComboBox strCombo = new JComboBox(fruits);
		strCombo.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED)
					return;
				
				String fruit = (String)e.getItem();
				
				for( int i = 0 ; i < fruits.length ; i++ ) {
					if( fruits[i].equals(fruit) ) {
						imgLabel.setIcon(images[i]);
						break;
					}						
				}					
			}
		});
		contentPane.add(strCombo);
		contentPane.add(imgLabel);
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComboActionEx();
	}
}
