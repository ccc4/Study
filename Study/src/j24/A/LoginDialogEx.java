package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LoginDialog extends JDialog {
	private JTextField idField = new JTextField(10);
	private JPasswordField pwdField = new JPasswordField(10);
	private JButton loginBtn = new JButton("Login");
	private JButton cancleBtn = new JButton("Cancle");
	
	public LoginDialog(JFrame frame, String title) {
		super(frame, title, true);
		this.setLayout(new GridLayout(3, 2));
		
		add(new JLabel("ID"));
		add(idField);
		add(new JLabel("Password"));
		add(pwdField);
		add(loginBtn);
		add(cancleBtn);
		
		this.loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idField.setText(idField.getText().trim());
				pwdField.setText(pwdField.getText().trim());
				setVisible(false);
			}
		});
		
		this.cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idField.setText("");
				pwdField.setText("");
				setVisible(false);
			}
		});		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				idField.setText("");
				pwdField.setText("");
			}
		});
		
		this.setSize(250, 150);
	}
	
	public String getId() {
		return idField.getText().length() == 0 ? 
				null : idField.getText();
	}
	public String getPwd() {
		return pwdField.getText().length() == 0 ? 
				null : pwdField.getText();
	}
}

public class LoginDialogEx extends JFrame {
	LoginDialog dialog = 
		new LoginDialog(this, "Login Dialog");
	
	public LoginDialogEx() {
		super("LoginDialogEx 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("로그인");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dialog.setVisible(true);				
				
				String id = dialog.getId();
				String pwd = dialog.getPwd();

				System.out.printf("ID : %s, PWD : %s\n", 
						id, pwd);
			}
		});
		
		this.addWindowListener(new WindowListener() {			
			public void windowOpened(WindowEvent e) {
				System.out.println("windowOpened");				
			}
			public void windowIconified(WindowEvent e) {
				System.out.println("windowIconified");				
			}
			public void windowDeiconified(WindowEvent e) {
				System.out.println("windowDeiconified");				
			}
			public void windowDeactivated(WindowEvent e) {
				System.out.println("windowDeactivated");				
			}
			public void windowClosing(WindowEvent e) {
				System.out.println("windowClosing");				
			}
			public void windowClosed(WindowEvent e) {
				System.out.println("windowClosed");
			}
			public void windowActivated(WindowEvent e) {
				System.out.println("windowActivated");				
			}
		});
		
		getContentPane().add(btn);
		setSize(350, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LoginDialogEx();
	}
}
