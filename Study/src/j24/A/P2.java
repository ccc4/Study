import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class P2 extends JFrame {
	Dial dial = new Dial(this, "Hello~");
	public P2() {
		super("제목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("Login");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dial.setVisible(true);
			}
		});
		
		
		
				
		getContentPane().add(btn);
		
		
		setSize(300, 300);
		setVisible(true);
	}
	
	class Dial extends JDialog {
		
		public Dial(JFrame frame, String title) {
			super(frame, title, true);
			setLayout(new GridLayout(3, 2));
			JLabel idLabel = new JLabel("ID");
			JLabel pwLabel = new JLabel("PW");
			JTextField idF = new JTextField();
			JPasswordField pwF = new JPasswordField();
			JButton btn1 = new JButton("확인");
			JButton btn2 = new JButton("취소");
			
			add(idLabel);
			add(idF);
			add(pwLabel);
			add(pwF);
			add(btn1);
			add(btn2);
			
			setSize(200, 200);
			
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(idF.getText().trim().length() == 0) {
						return;
					} else {
						System.out.printf("id: %s / pw: %s\n", idF.getText(), pwF.getText());
						setVisible(false);
						
						
					}
				}
			});
			
			btn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			
			this.addWindowListener(new WindowAdapter() {
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					idF.setText("");
					pwF.setText("");
				}
			});
		}
	}
	
	public static void main(String[] args) {
		new P2();
	}
}
