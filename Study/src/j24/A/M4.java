package j24.A;

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

public class M4 extends JFrame {
	MyDialog dialog = new MyDialog(this, "dial");
	
	public M4() {
		super("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Click");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				dialog.setModal(true);
				dialog.setVisible(true);
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
	}
	
	public static void main(String[] args) {
		new M4();
	}
}
