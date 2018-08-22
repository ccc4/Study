package d0819;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


public class P1 extends JFrame {
	Container contentPane;
	JLabel lb;
	
	
	P1() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();

		
		
		
		createToolBar();
		setSize(400, 200);
		setVisible(true);
	}
	
	void createToolBar() {
		JToolBar toolBar = new JToolBar("Kitae Menu");
		toolBar.setBackground(Color.LIGHT_GRAY);
		
		JButton btn = new JButton("New");
		btn.setToolTipText("������ �����մϴ�.");
		toolBar.add(btn);
		JButton abtn = new JButton(new ImageIcon("images/aaa.jpg"));
		abtn.setToolTipText("������ ���ϴ�.");
		toolBar.add(abtn);
		toolBar.addSeparator();
		JButton bbtn = new JButton(new ImageIcon("images/bbb.jpg"));
		bbtn.setToolTipText("������ �����մϴ�.");
		toolBar.add(bbtn);
		toolBar.add(new JLabel("search"));
		JTextField tf = new JTextField("text field");
		tf.setToolTipText("ã�����ϴ� ���ڿ��� �Է��ϼ���.");
		toolBar.add(tf);
		JComboBox<String> combo = new JComboBox<>();
		combo.addItem("Java");
		combo.addItem("C#");
		combo.addItem("C");
		combo.addItem("C++");
		toolBar.add(combo);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
	}
	
	
	public static void main(String[] args) {
		new P1();
	}
}
