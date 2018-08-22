import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class S8 extends JFrame {
	Container contentPane;
	
	public S8() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.add(new AddJPanel(), BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
		
	}
	
	class AddJPanel extends JPanel {
		JTextField tf;
		JButton btn;
		JTextArea ta;
		
		public AddJPanel() {
			tf = new JTextField(20);
			tf.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(tf.getText().isEmpty()){
						return;
					} else {
						ta.append(tf.getText() + "\n");
					}
					tf.setText("");
				}
			});
			btn = new JButton("Å¬¸¯");
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(tf.getText().isEmpty()){
						return;
					} else {
						ta.append(tf.getText() + "\n");
					}
					tf.setText("");
				}
			});
			ta = new JTextArea("ºó Ä­\n", 7, 20);
			ta.setLineWrap(true);
			
			add(tf);
			add(btn);
			add(new JScrollPane(ta));
		}
	}
	
	public static void main(String[] args) {
		new S8();
	}
}
