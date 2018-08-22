package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyDialog extends JDialog {
	JTextField tf = new JTextField(10);
	JButton okButton = new JButton("OK");

	public MyDialog(JFrame frame, String title) {
		super(frame, title, true);
		setLayout(new FlowLayout());
		add(tf);
		add(okButton);
		setSize(200, 100);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}

public class DialogEx extends JFrame {	
	MyDialog dialog = new MyDialog(this, "Test Dialog");

	public DialogEx() {
		super("DialogEx 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Show Dialog");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dialog.setModal(true);
				dialog.setVisible(true);				
				System.out.println("다이얼로그 종료");
			}
		});
		
		getContentPane().add(btn);
		setSize(250, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DialogEx();
	}
}
