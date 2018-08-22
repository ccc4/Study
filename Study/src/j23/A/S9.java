package j23.A;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class S9 extends JFrame {
	Container contentPane;
	String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", 
			"strawberry", "blackberry"};
	ImageIcon[] images = {
			new ImageIcon("images/icon1.png"), 
			new ImageIcon("images/icon2.png"), 
			new ImageIcon("images/icon3.png"), 
			new ImageIcon("images/icon4.png"), 
			};
	
	public S9() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JList strList = new JList(fruits);
		contentPane.add(strList);
		
		JList imageList = new JList();
		imageList.setListData(images);
		contentPane.add(imageList);
		
		JList scrollList = new JList(fruits);
		contentPane.add(new JScrollPane(scrollList));
		
		
		
		
		
		
		setSize(300, 300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new S9();
	}
}
