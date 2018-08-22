import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class P1 extends JFrame {
	Container contentPane;
	
	
	public P1() {
		super("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		
		
		
		
		setSize(500, 200);
		contentPane.add(new MyPanel(this), BorderLayout.NORTH);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		JButton inputBtn = new JButton("Input Name");
		JTextField tf = new JTextField(10);
		JButton confirmBtn = new JButton("Confirm");
		JButton messageBtn = new JButton("Message");
		JFrame frame;
		
		public MyPanel(JFrame frame) {
			this.frame = frame;
			setBackground(Color.LIGHT_GRAY);
			add(inputBtn);
			add(confirmBtn);
			add(messageBtn);
			add(tf);
			
			inputBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = JOptionPane.showInputDialog(frame, "이름을 입력하세요.");
					if(name != null) {
						tf.setText(name);
					}
				}
			});
			
			confirmBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame, "계속할것입니까?", "Contirm", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.CLOSED_OPTION) {
						tf.setText("Just Closed without Selection"); 
					} else if(result == JOptionPane.YES_OPTION) {
						tf.setText("Yes");
					} else {
						tf.setText("No");
					}
				}
			});
			
			messageBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(frame, "조심하세요", "Message", JOptionPane.ERROR_MESSAGE);
				}
			});
		}  
	}
	
	
	
	public static void main(String[] args) {
		new P1();
	}
}
