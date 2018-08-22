import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class M5 extends JFrame {
	MyDialog dialog = new MyDialog(this, "dial");
	
	public M5() {
		super("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Click");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				dialog.setModal(true);
				dialog.setVisible(true);
				String text = dialog.input();
				if(text == null) {
					return;
				} else {
					btn.setText(text);
				}
				
				System.out.println("다이알로그 종료");
			}
		});
		getContentPane().add(btn);
		setSize(250, 200);
		setVisible(true);
	}
	
	class MyDialog extends JDialog {
		JTextField tf = new JTextField(10);
		JButton okBtn = new JButton("Ok");
		
		public MyDialog(JFrame frame, String title) {
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
		
		String input() {
			if(tf.getText().trim().length() == 0) {
				return null;
			} else {
				return tf.getText();
			}
		}
	}
	
	public static void main(String[] args) {
		new M5();
	}
}
