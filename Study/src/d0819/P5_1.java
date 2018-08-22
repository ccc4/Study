package d0819;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

class dialogClass extends JDialog {
	JTextField tf = new JTextField(10);
	JButton okBtn = new JButton("Ok");
	
	public dialogClass(JFrame frame, String title) {
		super(frame, title, true);
		setLayout(new FlowLayout());
		add(tf);
		add(okBtn);
		setSize(200, 100);
		
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	String getInput() {
		if(tf.getText().length() == 0) {
			return null;
		} else {
			return tf.getText();
		}
	}
}


public class P5_1 extends JFrame {
	dialogClass dc = new dialogClass(this, "Hi");
	
	public P5_1() {
		super("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton btn = new JButton("click");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dc.setVisible(true);
				String text = dc.getInput();
				if(text == null) {
					return;
				} else {
//					JButton btn = (JButton) e.getSource();
					btn.setText(text);
				}
			}
		});

		getContentPane().add(btn);
		
		setSize(250, 200);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new P5_1();
	}
}
